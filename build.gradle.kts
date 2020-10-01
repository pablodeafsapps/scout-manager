buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Build.androidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.dokkaGradlePlugin)
        classpath(Build.ribbonizerPlugin)
        classpath(Build.fbCrashlyticsGradlePlugin)
        classpath(Build.googleServicesPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
    }
}

tasks.register("clean").configure {
    delete("build")
}