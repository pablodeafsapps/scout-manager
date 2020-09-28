const val kotlinVersion = "1.4.10"

object Build {
    object Versions {
        const val buildTools = "3.4.2"
        const val dokka = "0.9.17"
        const val ribbonizer = "2.0.0"
        const val fbCrashlyticsGradle = "2.0.0-beta04"
        const val googleServices = "4.2.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val dokkaGradlePlugin = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.dokka}"
    const val ribbonizerPlugin = "com.github.gfx.ribbonizer:ribbonizer-plugin:${Versions.ribbonizer}"
    const val fbCrashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.fbCrashlyticsGradle}"
    const val googleServicesPlugin = "com.google.gms:google-services:${Versions.googleServices}"

}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val dokka = "org.jetbrains.dokka"
    const val fbCrashlytics = "com.google.firebase.crashlytics"
    const val ribbonizer = "com.github.gfx.ribbonizer"
    const val version = "version.gradle"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val googleServices = "com.google.gms.google-services"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
    const val buildToolsVersion = "29.0.2"
}

// flavors
object Dev {
    const val dimension = "version"
    const val applicationIdSuffix = ".dev"
    const val versionNameSuffix = "-dev"
}

object Pre {
    const val dimension = "version"
    const val applicationIdSuffix = ".pre"
    const val versionNameSuffix = "-pre"
}

private object Pro

// librarias
object Libraries {
    // kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrow}"
    const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"
    const val anko = "org.jetbrains.anko:anko:${Versions.anko}"
    const val ankoCommons = "org.jetbrains.anko:anko-design:${Versions.anko}"
    const val ankoDesign = "org.jetbrains.anko:anko-commons:${Versions.anko}"
    // androidx
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    // google
    const val googleMaterial = "com.google.android.material:material:${Versions.googleMaterial}"
    // firebase
    const val fbAnalytics = "com.google.firebase:firebase-analytics:${Versions.fbAnalytics}"
    const val fbCrashlytics = "com.google.firebase:firebase-crashlytics:${Versions.fbCrashlytics}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    // koin
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinViewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    // retrofit
    const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    // testing
    const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val kotlinCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"
    const val testRunner = "androidx.test:runner:${Versions.androidxtestrunner}"
    const val testRules = "androidx.test:rules:${Versions.androidxtestrules}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    private object Versions {
        // core & kotlin
        const val coroutines = "1.3.9"
        const val coroutinesAdapter = "0.9.2"
        // androidx
        const val appCompat = "1.2.0"
        const val core = "1.5.0-alpha01"
        const val constraintLayout = "2.0.1"
        const val recyclerview = "1.1.0"
        const val cardView = "1.0.0"
        // 3rd party
        const val leakCanary = "2.2"
        const val fbAnalytics = "17.5.0"
        const val fbCrashlytics = "17.2.1"
        const val googleServices = "4.3.3"
        const val googleMaterial = "1.1.0-alpha08"
        const val koin = "2.2.0-alpha-1"
        const val arrow = "0.11.0"
        const val retrofit = "2.8.1"
        const val okhttp = "4.2.1"
        const val anko = "0.10.8"
        // test
        const val junit = "4.13"
        const val androidxtestrunner = "1.3.0"
        const val androidxtestrules = "1.3.0"
        const val espresso = "3.3.0"
        const val mockitoAndroid = "3.2.4"
        const val mockitoKotlin = "2.1.0"
    }
}