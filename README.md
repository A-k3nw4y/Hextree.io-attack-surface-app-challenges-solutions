# EvilCorp - Hextree Attack Surface Solvers

A personal project containing solver activities and POCs for the **Hextree.io Android Attack Surface** (`io.hextree.attacksurface`) CTF challenges.

## App Contents

The app is divided into component modules accessible from `MainActivity`:

- **Intents** (`Hextree_IntentsActivity`): Intent redirection, PendingIntents, and activity result handling.
- **Deeplinks** (`DeeplinksActivity`): Custom URI schemes (`hex://token`) and deep link parsing.
- **Broadcast Receivers** (`BroadcastReceiversActivity`): Intercepting and sending ordered/implicit broadcasts.
- **Services** (`ServicesActivity`): Started services, bound services, Messenger, and AIDL IPC.
- **Content Providers** (`ContentProvidersActivity`): Querying custom providers, file descriptor access, and URI permissions.
- **WebViews** (`WebviewsActivity`): JavaScript interface interaction and local file access.
- **Extra Catchers** (`extraActivities/`): Receiver activities (`Chall10CatcherActivity`, `Chall22Activity`, `Chall23Activity`) for intent callbacks.

## How to Run

### Requirements
- Android Studio
- Android Emulator (AVD) or Genymotion
- The `io.hextree.attacksurface` APK installed on the emulator

### Instructions

1. **Install Target APK on Emulator**:
   ```bash
   adb install /path/to/hextree-attacksurface.apk
   ```

2. **Clone the Repo**:
   ```bash
   git clone https://github.com/YOUR_USERNAME/EvilCorp.git
   ```

3. **Open in Android Studio**:
   - Open Android Studio -> **File** > **Open** -> Select the `EvilCorp` project folder.
   - Wait for Gradle to finish syncing.

4. **Run the App**:
   - Launch your AVD or Genymotion emulator.
   - Select the emulator in Android Studio's target device menu.
   - Click **Run** (`▶` / `Shift + F10`).
