package co.billionlabs.developerpreferencedemo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Apply developer-specific screen orientation
        // No more manual commenting/uncommenting!
        requestedOrientation = DeveloperConfig.getScreenOrientation()

        // Print configuration to logcat for verification
        DeveloperConfig.printCurrentConfig()

        // Create comprehensive configuration display view
        setContentView(createConfigurationDisplayView())

        // Skip tutorials based on developer preference
        if (!DeveloperConfig.shouldSkipTutorials()) {
            // Show tutorials for developers who want them
            DeveloperConfig.log("MainActivity", "Tutorials enabled for this developer")
        } else {
            DeveloperConfig.log("MainActivity", "Tutorials skipped for this developer")
        }

        // Example of using AWS bucket configuration
        initializeAwsConnection()

        // Example of using Python path
        setupPythonEnvironment()
    }

    private fun createConfigurationDisplayView(): ScrollView {
        val scrollView = ScrollView(this)
        val mainLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(DeveloperConfig.getFavoriteColor())
        }

        // Add title
        mainLayout.addView(createTitleText("BUILD VARIANT CONFIGURATION"))
        mainLayout.addView(createSeparator())

        // Add current variant info
        mainLayout.addView(createSectionTitle("CURRENT VARIANT"))
        mainLayout.addView(createConfigItem("FLAVOR", BuildConfig.FLAVOR))
        mainLayout.addView(createConfigItem("BUILD_TYPE", BuildConfig.BUILD_TYPE))
        mainLayout.addView(createConfigItem("VERSION_NAME", BuildConfig.VERSION_NAME))
        mainLayout.addView(createConfigItem("VERSION_CODE", BuildConfig.VERSION_CODE.toString()))
        mainLayout.addView(createConfigItem("APPLICATION_ID", BuildConfig.APPLICATION_ID))

        mainLayout.addView(createSeparator())

        // Add developer preferences
        mainLayout.addView(createSectionTitle("DEVELOPER PREFERENCES"))
        mainLayout.addView(createConfigItem("SKIP_TUTORIALS", BuildConfig.SKIP_TUTORIALS.toString()))
        mainLayout.addView(createConfigItem("PREFERRED_ORIENTATION", BuildConfig.PREFERRED_ORIENTATION))
        mainLayout.addView(createConfigItem("AWS_BUCKET", BuildConfig.AWS_BUCKET))
        mainLayout.addView(createConfigItem("PYTHON_PATH", BuildConfig.PYTHON_PATH))

        mainLayout.addView(createSeparator())

        // Add environment settings
        mainLayout.addView(createSectionTitle("ENVIRONMENT SETTINGS"))
        mainLayout.addView(createConfigItem("API_BASE_URL", BuildConfig.API_BASE_URL))
        mainLayout.addView(createConfigItem("ENABLE_LOGGING", BuildConfig.ENABLE_LOGGING.toString()))
        mainLayout.addView(createConfigItem("DEBUG", BuildConfig.DEBUG.toString()))

        mainLayout.addView(createSeparator())

        // Add instructions
        mainLayout.addView(createInstructionText())

        scrollView.addView(mainLayout)
        return scrollView
    }

    private fun createTitleText(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 24f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.parseColor("#2196F3"))
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 24)
        }
    }

    private fun createSectionTitle(text: String): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 18f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.parseColor("#FF5722"))
            setPadding(0, 16, 0, 12)
        }
    }

    private fun createConfigItem(key: String, value: String): LinearLayout {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 12, 16, 12)
            setBackgroundColor(Color.parseColor("#F5F5F5"))
        }

        val keyView = TextView(this).apply {
            text = key
            textSize = 16f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.parseColor("#333333"))
        }

        val valueView = TextView(this).apply {
            text = value
            textSize = 20f
            typeface = Typeface.MONOSPACE
            setTextColor(Color.parseColor("#4CAF50"))
            setPadding(0, 8, 0, 0)
        }

        layout.addView(keyView)
        layout.addView(valueView)

        // Add margin between items
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 0, 8)
        layout.layoutParams = params

        return layout
    }

    private fun createSeparator(): TextView {
        return TextView(this).apply {
            text = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
            textSize = 14f
            setTextColor(Color.parseColor("#CCCCCC"))
            gravity = Gravity.CENTER
            setPadding(0, 16, 0, 16)
        }
    }

    private fun createInstructionText(): TextView {
        return TextView(this).apply {
            text = """
                🔧 TESTING INSTRUCTIONS:

                1. Switch Build Variant in Android Studio:
                   View → Tool Windows → Build Variants

                2. Select different variants:
                   • colinDebug
                   • tatsuoDebug
                   • productionDebug/Release

                3. Build and run to see configuration changes

                4. Compare the values above between variants

                Notice how each developer gets their specific:
                • Orientation preferences
                • AWS bucket configurations
                • Python path settings
                • Tutorial preferences

                No manual code editing required! 🎉
            """.trimIndent()
            textSize = 14f
            setTextColor(Color.parseColor("#666666"))
            setPadding(16, 24, 16, 24)
            setBackgroundColor(Color.parseColor("#FFF3E0"))
        }
    }

    private fun initializeAwsConnection() {
        val bucket = DeveloperConfig.getAwsBucket()
        DeveloperConfig.log("AWS", "Connecting to bucket: $bucket")
    }

    private fun setupPythonEnvironment() {
        val pythonPath = DeveloperConfig.getPythonPath()
        DeveloperConfig.log("Python", "Using Python at: $pythonPath")
    }
}