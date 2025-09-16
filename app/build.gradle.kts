plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "co.billionlabs.developerpreferencedemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "co.billionlabs.developerpreferencedemo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "developer"
    productFlavors {
        create("tatsuo") {
            dimension = "developer"
            applicationIdSuffix = ".tatsuo"
            versionNameSuffix = "-tatsuo"

            // Tatsuo's developer preferences
            buildConfigField("Boolean", "SKIP_TUTORIALS", "false")
            buildConfigField("String", "PREFERRED_ORIENTATION", "\"portrait\"")
            buildConfigField("String", "FAVORITE_COLOR", "\"GREEN\"")
            buildConfigField("String", "AWS_BUCKET", "\"tatsuo-dev-bucket\"")
            buildConfigField("String", "PYTHON_PATH", "\"/usr/local/bin/python3\"")
        }

        create("colin") {
            dimension = "developer"
            applicationIdSuffix = ".colin"
            versionNameSuffix = "-colin"

            // Colin's developer preferences
            buildConfigField("Boolean", "SKIP_TUTORIALS", "true")
            buildConfigField("String", "PREFERRED_ORIENTATION", "\"portrait\"")
            buildConfigField("String", "FAVORITE_COLOR", "\"BLUE\"")
            buildConfigField("String", "AWS_BUCKET", "\"colin-dev-bucket\"")
            buildConfigField("String", "PYTHON_PATH", "\"/opt/homebrew/bin/python3\"")
        }

        create("production") {
            dimension = "developer"
            // No suffix for production builds

            // Production settings
            buildConfigField("Boolean", "SKIP_TUTORIALS", "false")
            buildConfigField("String", "PREFERRED_ORIENTATION", "\"portrait\"")
            buildConfigField("String", "AWS_BUCKET", "\"vibro-production-bucket\"")
            buildConfigField("String", "PYTHON_PATH", "\"python3\"")
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            // Debug-specific settings
            buildConfigField("Boolean", "ENABLE_LOGGING", "true")
            buildConfigField("String", "API_BASE_URL", "\"https://dev-api.vibro.com\"")
        }

        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            // Use debug signing for testing purposes
            signingConfig = signingConfigs.getByName("debug")

            // Release-specific settings
            buildConfigField("Boolean", "ENABLE_LOGGING", "false")
            buildConfigField("String", "API_BASE_URL", "\"https://api.vibro.com\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true  // Important: enables BuildConfig access
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}