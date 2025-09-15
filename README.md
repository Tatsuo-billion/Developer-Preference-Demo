# Developer Preference Demo

This is a demo Android project that explores **productFlavors** and **buildTypes** to solve developer workflow issues by eliminating manual code editing for different developer environments.

## Problem Solved

Previously, developers had to manually comment/uncomment code for different setups:
- Python path configurations
- Screen orientation preferences
- AWS bucket specifications
- Tutorial skip settings

This project demonstrates how Android Build Variants automatically handle these preferences per developer.

## Build Variants Architecture

### Product Flavors (Developer Dimension)
- **`tatsuo`** - Tatsuo's development environment
- **`colin`** - Colin's development environment
- **`production`** - Production deployment settings

### Build Types
- **`debug`** - Development builds with logging enabled
- **`release`** - Production builds with optimizations

## Configuration Examples

Each developer gets automatic configuration without code changes:

| Setting | Tatsuo | Colin | Production |
|---------|--------|-------|------------|
| Skip Tutorials | `false` | `true` | `false` |
| Orientation | `portrait` | `portrait` | `portrait` |
| AWS Bucket | `tatsuo-dev-bucket` | `colin-dev-bucket` | `vibro-production-bucket` |
| Python Path | `/usr/local/bin/python3` | `/opt/homebrew/bin/python3` | `python3` |

## Testing Different Variants

### Build Specific Variants
```bash
./gradlew assembleColinDebug        # Colin's debug build
./gradlew assembleTatsuoDebug       # Tatsuo's debug build
./gradlew assembleProductionRelease # Production release build
```

### Install and Compare
```bash
./gradlew installColinDebug
./gradlew installTatsuoDebug
# Different package IDs allow side-by-side installation
```

### Switch in Android Studio
1. Open `View → Tool Windows → Build Variants`
2. Select different variants (e.g., `colinDebug`, `tatsuoRelease`)
3. Build and run to see configuration differences

## Key Files

- **`app/build.gradle.kts`** - Build variants and BuildConfig field definitions
- **`DeveloperConfig.kt`** - Centralized configuration utility
- **`MainActivity.kt`** - Configuration display showing all BuildConfig values
- **`CLAUDE.md`** - Documentation for Claude Code development

## Features Demonstrated

- ✅ **No manual code editing** - Each developer gets their config automatically
- ✅ **Side-by-side installation** - Different application IDs per flavor
- ✅ **Environment isolation** - Separate AWS buckets, Python paths per developer
- ✅ **Build type differences** - Debug vs release API endpoints and logging
- ✅ **Configuration display** - Visual verification of current variant settings

## How It Works

The app displays a comprehensive configuration view showing:
- Current build variant information
- Developer-specific preferences from productFlavors
- Environment settings from buildTypes
- Large, easy-to-read text for quick evaluation

Switch build variants in Android Studio to see the configuration values change automatically - no more manual code editing required!

## Getting Started

1. Clone this repository
2. Open in Android Studio
3. Select a build variant in the Build Variants panel
4. Build and run to see the configuration display
5. Switch variants and compare the differences

This demonstrates how Android Build Variants can eliminate developer workflow pain points while maintaining clean, maintainable code.