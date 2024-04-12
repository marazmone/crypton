import org.jlleitschuh.gradle.ktlint.tasks.BaseKtLintCheckTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.marazmone.crypton.android"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    namespace = "com.marazmone.crypton.android"
    tasks.withType(BaseKtLintCheckTask::class).configureEach {
        workerMaxHeapSize.set("512m")
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2022.12.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(project(":shared"))
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material3:material3")
    val lifecycleVersion = "2.5.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
    implementation(kotlin("reflect"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime-livedata")

    val koinVersion = "3.3.1"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:3.5.6")
    implementation("io.insert-koin:koin-androidx-workmanager:$koinVersion")

    val navVersion = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$navVersion")

    val work_version = "2.7.1"
    implementation("androidx.work:work-runtime-ktx:$work_version")
    testImplementation("junit:junit:4.13.2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}
