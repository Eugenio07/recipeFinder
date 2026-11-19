# Recipe Finder — Multi-Module Clean Architecture

![Kotlin](https://img.shields.io/badge/Kotlin-1.6.10-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-minSdk%2024%20%7C%20compileSdk%2031-3DDC84?style=flat&logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture%20%2B%20MVVM-0A66C2?style=flat)
![DI](https://img.shields.io/badge/DI-Dagger%20Hilt-orange?style=flat)

Multi-module native **Android** application built with **Clean Architecture**, **MVVM**, **Dagger Hilt**, **Room**, **Retrofit**, **Coroutines / LiveData**, and **Jetpack Navigation**. Consumes [TheMealDB](https://www.themealdb.com/api.php) and [REST Countries](https://restcountries.com/) APIs to discover recipes, filter by location-aware cuisines, and persist favorites locally.

---

## ✨ Features

- **Search by Name:** Instant recipe search from the home screen (e.g., `"honey"`, `"beef"`, `"apple"`).
- **Multi-Criteria Browsing:** Explore recipes by **Country / Cuisine**, **Category**, or **Main Ingredient**, with expandable descriptions for categories and ingredients.
- **Location-Aware Country Shortcut:** Uses **Google Play Services Location** (`FusedLocationProviderClient`) + `Geocoder` and **Dexter** runtime permissions to detect the user's country, cross-reference REST Countries metadata with supported cuisines, and surface a one-tap quick filter button (falling back to `"Unknown"` if permission is denied or the country is not listed by the API).
- **Random Recipe Discovery:** Fetch a random dish directly from the home menu.
- **Recipe Detail & Offline Favorites:** `CollapsingToolbarLayout` view featuring dish imagery (**Glide**), up to 20 ingredient/measure pairs, step-by-step preparation instructions, and persistent local **Room** favorites.

---

## 🧱 Architecture & Multi-Module Structure

The project enforces strict dependency boundaries across **4 Gradle modules** (`:app` → `:usecases` → `:data` → `:domain`):

```text
├── domain/      # Pure Kotlin — domain entities (Recipe, Category, Country, Ingredient), Either<L, R>, Event<T>
├── data/        # Pure Kotlin — RecipeRepository, CountriesRepository, Remote/Local/Location/Permission contracts
├── usecases/    # Pure Kotlin — RecipeUseCases & CountryUseCases decoupling business logic from Android
└── app/         # Android — Fragments (DataBinding), ViewModels, Navigation SafeArgs, Room DB, Retrofit, Hilt DI
```

### Key Architectural Highlights
- **Explicit Error & Event Modeling:** Custom `Either<L, R>` sealed type in `:domain` and `Event<T>` wrapper for one-shot navigation and feedback events.
- **Local Caching & Persistence:** Room database (`RecipeDataBase` v4) caches categories, countries, and ingredients while persisting user favorites offline.
- **Type-Safe Navigation:** Single-activity Jetpack Navigation (`NavHostFragment`) with SafeArgs and `@Parcelize` payloads.
- **Scoped Coroutine ViewModels:** `ScopedViewModel` base class managing `SupervisorJob` lifecycles with injected dispatchers.

---

## 🛠️ Tech Stack

| Category | Libraries & Versions |
| :--- | :--- |
| **Language & Build** | Kotlin `1.6.10` · AGP `7.0.4` · Gradle `7.0.2` · Java `8` |
| **Architecture & DI** | Clean Architecture (`4` modules) · MVVM · Dagger Hilt `2.36` (app) / `2.37` (test) |
| **Jetpack & UI** | Navigation & SafeArgs `2.3.5` · Lifecycle ViewModel & LiveData `2.4.0` · DataBinding · Material `1.4.0` · ConstraintLayout `2.1.2` · RecyclerView `1.2.1` |
| **Async & Storage** | Kotlin Coroutines `1.6.0-native-mt` · Room `2.4.0` (`kapt`) · DataStore Preferences `1.0.0` · Glide `4.12.0` |
| **Networking** | Retrofit `2.9.0` · Moshi `1.9.3` · OkHttp Profiler `1.0.8` |
| **Location & Utils** | Google Play Services Location `19.0.0` · Dexter `6.2.3` · Logger `2.2.0` |
| **Testing** | JUnit `4.13.2` · Mockito Kotlin `2.2.0` · Mockito Inline `4.2.0` · Arch Core Testing `2.1.0` · Hilt Android Testing `2.37` · Espresso `3.4.0` · OkHttp3 IdlingResource `1.0.0` |

---

## 🧪 Testing

The codebase includes **unit**, **Hilt integration**, and **Espresso UI** tests across the architecture layers:

- **`:data` Unit Tests:** `CountriesRepositoryTest` (6 tests) and `RecipeRepositoryTest` (14 active tests) verifying cache hits, permission states, favorites CRUD, and API filtering.
- **`:usecases` Unit Tests:** `CountryUseCaseTest` (3 tests) and `RecipeUseCaseTest` (10 tests).
- **`:app` ViewModel Unit Tests:** `PrincipalViewModelTest`, `SecondaryViewModelTest`, `ListViewModelTest`, and `DetailViewModelTest` using `InstantTaskExecutorRule` and Mockito.
- **`:app` Instrumentation & UI Tests (`androidTest`):**
  - Custom `MyTestRunner` (`HiltTestApplication`).
  - Hilt ViewModel integration tests (`PrincipalIntegrationTest`, `SecondaryIntegrationTest`, `ListIntegrationTest`, `DetailIntegrationTest`).
  - End-to-end Espresso flow (`ChangeTextBehaviorTest`) synchronized via `OkHttp3IdlingResource`.

```bash
# Run JVM unit tests across all modules
./gradlew test

# Run instrumentation & Espresso tests (requires a connected device or emulator)
./gradlew connectedAndroidTest
```

---

## 🚀 Getting Started

### Prerequisites
- **JDK 8+**
- **Android Studio** with Android SDK (`compileSdk 31`, `minSdk 24`, `targetSdk 30`)

> **No API keys required:** Both [TheMealDB](https://themealdb.com/api/json/v1/1/) and [REST Countries](https://restcountries.com/v2/) endpoints used by the app are public.

### Build & Run
```bash
./gradlew :app:assembleDebug
```
Or open the project in Android Studio and run the **`app`** configuration on an Android emulator or physical device.

---

## 📸 Screenshots

> _Add emulator or device captures to showcase the search flow, country shortcut, recipe list, and detail screen:_

| Home | Country Shortcut | Recipe List | Recipe Detail |
| :---: | :---: | :---: | :---: |
| _Coming soon_ | _Coming soon_ | _Coming soon_ | _Coming soon_ |

---

## 👤 Author

**Eugenio Martínez** — Senior Mobile Developer · Android & Kotlin Multiplatform (KMP)  
[![GitHub](https://img.shields.io/badge/GitHub-Eugenio07-181717?style=flat&logo=github)](https://github.com/Eugenio07)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-eugeniormartinez-0A66C2?style=flat&logo=linkedin)](https://www.linkedin.com/in/eugeniormartinez)

