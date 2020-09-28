import org.jetbrains.dokka.gradle.DokkaTask
import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.androidApplication)

    id(Plugins.kotlinAndroid)
    // add automatic documentation generator feature
    id(Plugins.dokka)
    // add crash analytics feature
    id(Plugins.fbCrashlytics)
    // add overlaid launcher icons feature
    id(Plugins.ribbonizer)
    // add version management feature
    id("version.gradle.kts")
    id(Plugins.googleServices)
}

val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream("keystore.properties"))

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "org.deafsapps.android.scoutmanager"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = Libraries.instrumentationRunner
    }
    signingConfigs {
        register("release") {
            storeFile = keystoreProperties["storeFile"]?.let { file(it) }
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions("version")
    productFlavors {
        create("dev") {
            applicationIdSuffix = Dev.applicationIdSuffix
            versionNameSuffix = Dev.versionNameSuffix
        }
        create("pre") {
            applicationIdSuffix = Pre.applicationIdSuffix
            versionNameSuffix = Pre.versionNameSuffix
        }
        create("pro") {
        }
    }
    variantFilter {
        val names = """variant.flavors*.name""".toRegex()
        if (buildType.name == "release" && (names.containsMatchIn("dev") || names.containsMatchIn("pre"))) {
            setIgnore(true)
        }
    }
    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
    }
    lintOptions {
        isAbortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
        skipEmptyPackages = true   // skip empty packages
        skipDeprecated = true   // skip deprecated
        noStdlibLink = true   // skip documentation related to kotlin-stdlib
    }
}

//ribbonizer {
//    builder { variant, iconFile ->
//    // change ribbon color by product flavors
//        lateinit var filter
//        if (variant.flavorName == "dev") {
//            filter = greenRibbonFilter(variant, iconFile)
//        } else if (variant.flavorName == "pre") {
//            filter = yellowRibbonFilter(variant, iconFile)
//        } else if (variant.buildType.name == "debug") {   // pro
//            filter = customColorRibbonFilter(variant, iconFile, "#FF0000")
//        } else {   // pro - release
//            return
//        }
//        filter.label = variant.versionName.replaceFirst("-.*$", "")
//        return filter
//    }
//}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar")) })
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.anko)
// other modules
    implementation(project(":presentation-layer"))
    implementation(project(":domain-layer"))
    implementation(project(":data-layer"))
// 3rd party libraries
    implementation(Libraries.koinAndroid)
    implementation(Libraries.fbAnalytics)
    implementation(Libraries.fbCrashlytics)
    debugImplementation(Libraries.leakCanary)
}