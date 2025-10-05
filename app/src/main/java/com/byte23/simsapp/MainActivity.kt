package com.byte23.simsapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Try to open the system Mobile Network list screen directly.
        // Fall back to broader Settings screens if unavailable.
        if (openMobileNetworkList() || openWirelessSettings() || openMainSettings()) {
            // We launched a target; this activity is just a trampoline.
            finish()
            return
        }

        // If nothing resolved (highly unlikely), just finish gracefully.
        finish()
    }

    private fun canResolve(intent: Intent): Boolean {
        return intent.resolveActivity(packageManager) != null
    }

    private fun tryStart(intent: Intent): Boolean {
        return try {
            if (canResolve(intent)) {
                startActivity(intent)
                true
            } else false
        } catch (_: Exception) {
            false
        }
    }

    private fun openMobileNetworkList(): Boolean {
        // Known AOSP Settings activity class names to try, ordered by likelihood.
        // These are treated as simple strings since they are internal implementation details.
        val candidates = listOf(
            // Most modern AOSP builds
            "com.android.settings.Settings${'$'}MobileNetworkListActivity",
            // Variations seen across versions/vendors
            "com.android.settings.Settings${'$'}MobileNetworkSettingsActivity",
            "com.android.settings.Settings${'$'}NetworkProviderSettingsActivity",
            "com.android.settings.Settings${'$'}SimSettingsActivity"
        )


        for (className in candidates) {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                component = ComponentName("com.android.settings", className)
            }
            if (tryStart(intent)) return true
        }

        // As an additional implicit fallback specifically for network/operators
        val implicitFallbacks = listOf(
            Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS),
            Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
        )

        for (intent in implicitFallbacks) {
            if (tryStart(intent)) return true
        }

        return false
    }

    private fun openWirelessSettings(): Boolean {
        return tryStart(Intent(Settings.ACTION_WIRELESS_SETTINGS))
    }

    private fun openMainSettings(): Boolean {
        return tryStart(Intent(Settings.ACTION_SETTINGS))
    }
}
