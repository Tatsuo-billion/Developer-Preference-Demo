package co.billionlabs.developerpreferencedemo

import android.content.pm.ActivityInfo
import android.util.Log

/**
 * Centralized configuration utility that provides access to build variant specific settings.
 * This eliminates the need for manual code commenting/uncommenting for different developer setups.
 */
object DeveloperConfig {

    /**
     * Get the preferred screen orientation based on the current build variant
     */
    fun getScreenOrientation(): Int {
        return when (BuildConfig.PREFERRED_ORIENTATION) {
            "portrait" -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            "reverse_portrait" -> ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
            "landscape" -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            "reverse_landscape" -> ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
            else -> ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    /**
     * Check if tutorials should be skipped for this developer
     */
    fun shouldSkipTutorials(): Boolean = BuildConfig.SKIP_TUTORIALS

    /**
     * Get the AWS bucket configuration for this build variant
     */
    fun getAwsBucket(): String = BuildConfig.AWS_BUCKET

    /**
     * Get the Python path for this developer's environment
     */
    fun getPythonPath(): String = BuildConfig.PYTHON_PATH

    /**
     * Get the API base URL (debug vs release)
     */
    fun getApiBaseUrl(): String = BuildConfig.API_BASE_URL

    /**
     * Check if logging is enabled for this build
     */
    fun isLoggingEnabled(): Boolean = BuildConfig.ENABLE_LOGGING

    /**
     * Conditional logging that respects the build configuration
     */
    fun log(tag: String, message: String) {
        if (isLoggingEnabled()) {
            Log.d(tag, message)
        }
    }

    /**
     * Print current configuration for debugging
     */
    fun printCurrentConfig() {
        log("DeveloperConfig", "=== Build Configuration ===")
        log("DeveloperConfig", "Build Type: ${BuildConfig.BUILD_TYPE}")
        log("DeveloperConfig", "Flavor: ${BuildConfig.FLAVOR}")
        log("DeveloperConfig", "Skip Tutorials: ${shouldSkipTutorials()}")
        log("DeveloperConfig", "Preferred Orientation: ${BuildConfig.PREFERRED_ORIENTATION}")
        log("DeveloperConfig", "AWS Bucket: ${getAwsBucket()}")
        log("DeveloperConfig", "Python Path: ${getPythonPath()}")
        log("DeveloperConfig", "API Base URL: ${getApiBaseUrl()}")
        log("DeveloperConfig", "Logging Enabled: ${isLoggingEnabled()}")
        log("DeveloperConfig", "===========================")
    }
}