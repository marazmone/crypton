import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
        classpath("io.realm.kotlin:gradle-plugin:1.5.1")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.6.1")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    id("org.jlleitschuh.gradle.ktlint").version("10.3.0")
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    ktlint {
        version.set("0.45.2")
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        additionalEditorconfigFile.set(file("$rootDir/.editorconfig"))
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
        }

        filter {
            exclude("**/generated/**")
        }
    }

    detekt {
        toolVersion = "1.22.0"
        parallel = true
        config = files("$rootDir/detekt-config.yml")
        autoCorrect = true
        buildUponDefaultConfig = false
    }

    tasks.detekt {
        jvmTarget = "1.8"
        reports {
            xml {
                required.set(true)
                outputLocation.set(file("build/reports/detekt.xml"))
            }
            html {
                required.set(true)
                outputLocation.set(file("build/reports/detekt.html"))
            }
        }
    }

    tasks.detektBaseline { jvmTarget = "1.8" }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
