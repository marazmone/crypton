# ₿ CryptoN

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.7.x-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-7.x-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-7.x-blue?style=flat)](https://gradle.org)

CryptoN project presents a modern approach to
[Kotlin KMM](https://kotlinlang.org/lp/mobile) application development. This project utilizes
popular tools, libraries, linters, Gradle plugins, testing frameworks. It is a complete sample of a fully
functional Android/IOS application.

To be continued...

## 🌐 Localization

[![Crowdin](https://badges.crowdin.net/crypton/localized.svg)](https://crowdin.com/project/crypton)

[🇳🇱](https://crowdin.com/project/crypton/nl)
[🇫🇷](https://crowdin.com/project/crypton/fr)
[🇩🇪](https://crowdin.com/project/crypton/de)
[🇮🇹](https://crowdin.com/project/crypton/it)
[🇵🇹](https://crowdin.com/project/crypton/pt-PT)
[🇪🇸](https://crowdin.com/project/crypton/es-ES)

## CI Pipeline

CI is utilizing [GitHub Actions](https://github.com/features/actions). Complete GitHub Actions config is located in
the [.github/workflows](.github/workflows) folder.

### Pull Request Verification

Series of workflows run (in parallel) for every opened PR and after merging PR to the `master` branch:

* `./gradlew ktlintCheck` - runs ktlint
* `./gradlew detekt` - runs detekt
* `./gradlew :androidApp:assembleDebug --no-build-cache` - build debug

