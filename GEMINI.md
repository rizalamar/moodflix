# MoodFlix: Cine-Weather Matcher System - Project Instructions

## Project Summary
An anti-mainstream, intelligent entertainment aggregator system that recommends curated Movies and Anime dynamically based on the user's real-time local weather. Operating as an API Aggregator (BFF - Backend for Frontend) architecture, the platform securely identifies users, tracks their coordinates via frontend GPS, queries multiple live third-party public APIs, maps weather conditions to specific psychological "mood vibes", and serves a consolidated data payload.

## Core Tech Stack
- **Language:** Java 21 (LTS)
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security & JWT (Roles: USER)
- **Database:** Spring Data JPA / Hibernate (PostgreSQL) with JPA Auditing
- **Documentation:** Springdoc OpenAPI (Swagger)

## Architectural Mandates
1. *Entity Design:*
    - All domain entities must extend an `AbstractAuditingEntity` (capturing created_by, created_date, etc.).
    - Use Lombok's `@Getter` and `@Setter` instead of `@Data`.
    - Primary Keys must be **UUID**.
    - Implement **Enums** for weather states, interaction logs, and user roles.

2. *API Aggregator & Mapping Logic:*
    - The Spring Boot backend acts as a proxy/aggregator to eliminate CORS issues and heavy logic on the React frontend.
    - **Integration Points:**
        1. **OpenWeatherMap API:** To fetch current weather using precise `lat` (latitude) and `lon` (longitude).
        2. **TMDB API:** To fetch high-rated matching Hollywood movies using numeric *Genre IDs*.
        3. **Jikan API (MyAnimeList):** To fetch curated anime using string-based genre mappings.
    - **Weather-to-Mood Matrix:**
        - `Rain / Thunderstorm` $\rightarrow$ Cozy/Melancholy Mood (Romance, Mystery, Slice of Life).
        - `Clear / Sunny` $\rightarrow$ High Energy/Adventure Mood (Action, Adventure, Sports).
        - `Clouds / Mist` $\rightarrow$ Dark/Psychological Mood (Thriller, Horror, Supernatural).

3. *Security & Access Control:*
    - **Guest / Public (Unauthenticated):** Access to Register, Login, Swagger Docs, and Default Static Recommendations (e.g., global trending movies).
    - **User (Authenticated via JWT):** Full access to real-time GPS-based Weather matching and personalized recommendation history.

4. *Clean Controller with Custom Argument Resolvers:*
    - Implement `HandlerMethodArgumentResolver` untuk menyuntikkan data request secara otomatis ke parameter Controller.
    - **`@CurrentUser` Resolver:** Otomatis mengekstrak JWT token dari SecurityContext dan menyajikannya sebagai objek domain `User` atau custom metadata record (e.g., `UserPrincipal`), sehingga Controller tidak perlu menyentuh class `Authentication` bawaan Spring secara langsung.
    - **`@GeoLocation` Resolver:** Otomatis menangkap, memvalidasi, dan membungkus *query parameters* `lat` dan `lon` menjadi Java Record `GeoLocation(double lat, double lon)` yang siap pakai.

5. *Data Transfer (DTOs):*
    - Use Java 21 **Records** for all API Requests, internal third-party API mappings, and final JSON Responses.
    - Wrap all API responses in a consistent `WebResponse<T>` envelope.

6. *Validation:*
    - Use `@Valid` in Controllers for input validation (e.g., memastikan koordinat berada dalam rentang geografis yang valid).
    - Use a dedicated **`ValidationService`** for business rules.

## Naming Conventions
- Packages: `com.rizalamar.moodflix`
- Sub-packages: `domain`, `repository`, `service`, `controller`, `dto`, `security`, `config`, `exception`, `client`, `resolver`.
- Database Tables: snake_case.
- Variables & Methods: camelCase.

## Key Domains
- **User:** (Id, Name, Email, Password, Role Enum [USER])
- **UserPreference:** (Id, User UUID [One-to-One], FavoriteGenres `List<String>`, DislikedGenres `List<String>`)
- **WatchHistory:** (Id, User UUID, MediaId `String`, MediaType Enum [MOVIE, ANIME], WatchedAt `LocalDateTime`, WeatherConditionAtTime `String`)

---

## Future Roadmap (Phase 2)
- **Frontend Optimization Unified Endpoint (BFF Architecture):**
    - Create a single consolidated endpoint `GET /api/v1/recommendations/dashboard?lat={lat}&lon={lon}` mapped directly to frontend components.
- **Performance Optimization (Caching Engine):**
    - Implement **Spring Cache abstraction** (Caffeine/Redis) to cache API response payloads for **30 minutes** to drastically reduce network latency and avoid third-party rate limits.
- **DiceBear Dynamic Smart Avatars:**
    - Integrate the **Dicebear Avatars API** to dynamically change user profile avatars based on the real-time weather of their location (e.g., wearing a raincoat or sunglasses).
- **AI Mood Adjustment Engine:**
    - Allow users to input custom text context (e.g., *"Saya sedih walaupun di luar cerah"*) and use LLM parsing to adjust query parameters.