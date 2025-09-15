# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build System

This is an Android project using Gradle with Kotlin DSL. The build system is configured with extensive build variants to eliminate manual code editing for developer preferences.

### Common Commands

**Build specific variants:**
```bash
./gradlew assembleColinDebug        # Colin's development build
./gradlew assembleTatsuoDebug       # Tatsuo's development build
./gradlew assembleProductionRelease # Production release build
```

**Testing:**
```bash
./gradlew test                      # Run all unit tests
./gradlew testColinDebugUnitTest    # Run tests for Colin's debug variant
./gradlew connectedAndroidTest      # Run instrumentation tests on device
```

**Code Quality:**
```bash
./gradlew lint                      # Run lint on default variant
./gradlew lintColinDebug           # Run lint on specific variant
./gradlew check                    # Run all verification tasks
```

## Architecture

### Build Variants System

The project implements a sophisticated build variants architecture using productFlavors and buildTypes to solve developer workflow issues:

**Product Flavors (Developer Dimension):**
- `tatsuo` - Tatsuo's developer environment
- `colin` - Colin's developer environment
- `production` - Production deployment

**Build Types:**
- `debug` - Development builds with logging enabled
- `release` - Production builds with optimizations

**Configuration Values:**
Each flavor defines BuildConfig fields for:
- `SKIP_TUTORIALS` - Whether to skip onboarding tutorials
- `PREFERRED_ORIENTATION` - Screen orientation preference
- `AWS_BUCKET` - Environment-specific AWS bucket
- `PYTHON_PATH` - Developer-specific Python executable path
- `API_BASE_URL` - Debug vs production API endpoints
- `ENABLE_LOGGING` - Build-specific logging control

### Configuration Access Pattern

**DeveloperConfig.kt** (`app/src/main/java/co/billionlabs/developerpreferencedemo/DeveloperConfig.kt`) provides centralized access to build configuration:

```kotlin
// Screen orientation
requestedOrientation = DeveloperConfig.getScreenOrientation()

// AWS configuration
val bucket = DeveloperConfig.getAwsBucket()

// Python environment
val pythonPath = DeveloperConfig.getPythonPath()
```

This eliminates manual code commenting/uncommenting that was previously required for:
- Screen orientation switching
- Python path configuration
- AWS bucket management
- Tutorial preferences

### Project Structure

**Core Configuration:**
- `app/build.gradle.kts` - Build variants and BuildConfig field definitions
- `gradle.properties` - Project-wide Gradle settings

**Application Code:**
- `DeveloperConfig.kt` - Centralized configuration utility
- `MainActivity.kt` - Example implementation showing configuration usage

### Build Variant Usage

When working with this codebase:

1. **Choose appropriate build variant** based on developer or deployment target
2. **Use DeveloperConfig utility** instead of hardcoding values
3. **Test builds** after configuration changes: `./gradlew assembleColinDebug assembleTatsuoDebug assembleProductionRelease`
4. **Verify configuration access** through BuildConfig fields rather than manual switches

The build variants eliminate the need for manual code editing when switching between developer environments or deployment targets.
- when building new feature or new things to build, create a plan for it including how we can test each step. Dont implement immediately.
- You can execute IDE Refresh if that is the issue why app isn't building without my permission