# SIMS

Simple Android utility that opens the system Mobile Network list screen (MobileNetworkListActivity) so you can quickly view and manage configured mobile networks (e.g., SIMs/eSIM profiles).

> Note: Availability of the target system activity can vary by device/OEM and Android version. See Limitations for details.

## Overview

SIMS is a one-tap shortcut to the system Mobile Network list. Instead of navigating deep into Settings, launch SIMS to jump straight to the list of mobile networks configured on the device.

## Features

- Direct shortcut to the Mobile Network list screen
- Zero configuration; no special permissions required
- Lightweight and fast; designed for convenience

## Requirements

- An Android device that exposes the Mobile Network list screen via a launchable system activity (commonly present on AOSP-based builds)
- Android version: varies by device; modern versions typically supported
- Android Studio (for building from source)

## Build & Run (Android Studio)

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Select a connected device or emulator.
4. Click Run to install and start the app.

If you prefer CLI, use `./gradlew assembleDebug` to build and then install the APK with `adb install -r app/build/outputs/apk/debug/app-debug.apk` (paths may vary by module).

## Usage

- Launch the SIMS app from the launcher.
- It will attempt to open the system’s Mobile Network list screen directly.
- If the target screen is unavailable, you may be redirected to the main Settings app (behavior may vary based on implementation and device).

## Permissions

- No special runtime permissions are expected. The app uses an intent to open a system Settings activity.

## Limitations

- OEMs and Android versions can rename, relocate, or restrict the Mobile Network list activity. On such devices, the shortcut may not work or may open a fallback.
- Some custom ROMs or enterprise-managed devices may block direct access to internal Settings screens.

## Troubleshooting

- Ensure the device has a Settings screen for mobile networks.
- Check `adb logcat` for intent resolution errors when launching the app.
- If the device does not expose the activity, there may be no reliable programmatic shortcut.

## Roadmap

- Optional: detect availability and present clearer UI or guidance
- Optional: provide fallbacks to closely related Settings screens when the exact target is missing

## Contributing

Contributions are welcome! Please open an issue to discuss changes or submit a PR.

## License

BSD-3-Clause. See LICENSE file for details.

## Disclaimer

This project is not affiliated with Google or any device manufacturer. Behavior depends on the device’s Settings implementation and Android version.
