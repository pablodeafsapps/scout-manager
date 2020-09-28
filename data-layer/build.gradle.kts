plugins {
    id(Plugins.androidLibrary)
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
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = Libraries.instrumentationRunner
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions("version")
    productFlavors {
        //TODO: update 'API_URL' value for 'pre' and 'pro' when available
        create("dev") {
            buildConfigField("String", "API_URL", "\"https://virtserver.swaggerhub.com/plexus-tech/60dias-expenses-api/1.0.0/\"")
            setDimension("version")
        }
        create("pre") {
            buildConfigField("String", "API_URL", "\"https://virtserver.swaggerhub.com/plexus-tech/60dias-expenses-api/1.0.0/\"")
            setDimension("version")
        }
        create("pro") {
            buildConfigField("String", "API_URL", "\"https://virtserver.swaggerhub.com/plexus-tech/60dias-expenses-api/1.0.0/\"")
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
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.retrofitCoroutinesAdapter)
    // other modules
    implementation(project(":domain-layer"))
    // 3rd party libraries
    implementation(Libraries.koinAndroid)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshi)
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