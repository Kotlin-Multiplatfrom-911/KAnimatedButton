# KAnimatedButton

An animated, brand-neutral [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) button for
Kotlin Multiplatform — supporting **Android, iOS, Desktop (JVM), and Web (Wasm/JS)**. It animates between an
expanded label state and a collapsed circular loading state, with filled and outlined variants in several shapes.

## Installation

Add the dependency to your `commonMain` source set:

```kotlin
// build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.john-safwat:kanimatedbutton:0.1.0")
        }
    }
}
```

## Usage

```kotlin
import io.github.johnsafwat.kanimatedbutton.KAnimatedButton
import io.github.johnsafwat.kanimatedbutton.KAnimatedButtonType

var isLoading by remember { mutableStateOf(false) }

KAnimatedButton(
    buttonType = KAnimatedButtonType.LARGE_RECTANGLE,
    title = "Submit",
    isEnabled = true,
    isLoading = isLoading,
    onClick = { isLoading = true },
)
```

Key parameters: `buttonType` (filled/outlined × rounded/rectangle in large/medium/small), `title` or a custom `child`
slot, `isEnabled`, `isLoading`, `wrapContentWidth`, plus overrides for `color`, `borderColor`, `borderRadius`,
`elevation`, `textStyle`, and sizing. Default colors come from `KAnimatedButtonDefaults` and are fully overridable.

A runnable showcase of every variant lives in [`app/shared` `App.kt`](./app/shared/src/commonMain/kotlin/io/github/johnsafwat/kanimatedbutton/App.kt).

---

## About this repository

This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM), Server.

* [/app/iosApp](./app/iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/app/shared](./app/shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./app/shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./app/shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./app/shared/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/core](./core/src) is for the code that will be shared between all targets in the project.
  The most important subfolder is [commonMain](./core/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

* [/server](./server/src/main/kotlin) is for the Ktor server application.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :app:androidApp:assembleDebug`
- Desktop app:
  - Hot reload: `./gradlew :app:desktopApp:hotRun --auto`
  - Standard run: `./gradlew :app:desktopApp:run`
- Server: `./gradlew :server:run`
- Web app:
  - Wasm target (faster, modern browsers): `./gradlew :app:webApp:wasmJsBrowserDevelopmentRun`
  - JS target (slower, supports older browsers): `./gradlew :app:webApp:jsBrowserDevelopmentRun`
- iOS app: open the [/app/iosApp](./app/iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :app:shared:testAndroidHostTest`
- Desktop tests: `./gradlew :app:shared:jvmTest`
- Server tests: `./gradlew :server:test`
- Web tests:
  - Wasm target: `./gradlew :app:shared:wasmJsTest`
  - JS target: `./gradlew :app:shared:jsTest`
- iOS tests: `./gradlew :app:shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).