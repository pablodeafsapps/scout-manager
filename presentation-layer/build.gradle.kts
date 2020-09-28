plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    // add automatic documentation generator feature
    id(Plugins.dokka)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        multiDexEnabled = true
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = Libraries.testRunner
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions("version")
    productFlavors {
        create("dev") {
            setDimension("version")
        }
        create("pre") {
            setDimension("version")
        }
        create("pro") {
            setDimension("version")
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
        getByName("test") { java.srcDir("src/test/kotlin") }
        getByName("androidTest") { java.srcDir("src/androidTest/kotlin") }
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
    val dokka by getting(org.jetbrains.dokka.gradle.DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokka"
        skipEmptyPackages = true   // skip empty packages
        skipDeprecated = true   // skip deprecated
        noStdlibLink = true   // skip documentation related to kotlin-stdlib
    }
}

dependencies {
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.anko)
    implementation(Libraries.ankoCommons)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.cardview)
    // other modules
    implementation(project(":domain-layer"))
    // 3rd party libraries
    implementation(Libraries.koinAndroid)
    implementation(Libraries.koinViewmodel)
    // testing dependencies - Unit Test
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockitoKotlin)
    testImplementation(Libraries.kotlinCoroutinesTest)
    // koin testing tools
    testImplementation(Libraries.koinTest)
    // testing dependencies - Instrumentation Test
    androidTestImplementation(Libraries.mockitoAndroid)
    androidTestImplementation(Libraries.testRunner)
    androidTestImplementation(Libraries.testRules)
    androidTestImplementation(Libraries.espresso)
    // koin testing tools
    androidTestImplementation(Libraries.koinTest) {
        exclude("group", "org.mockito")
        exclude("group", "mockito-inline")
    }
}