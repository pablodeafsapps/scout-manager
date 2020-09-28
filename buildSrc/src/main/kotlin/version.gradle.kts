import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

/**
 * This Gradle file manages the app versioning. It makes use of a 'version.properties' files which
 * has to be located int he project root and must include 3 values: VERSION_MAJOR, VERSION_MINOR,
 * and 'VERSION_BUILD'. In case a 'release' build is performed, 'VERSION_BUILD' is incremented.
 *
 * @author Pablo L. Sordo
 * @since 1.0
 */

plugins {
    id("com.android.application")
}

val versionPropertiesFile = rootProject.file("version.properties")

if (versionPropertiesFile.canRead()) {
    val versionProperties = Properties()
    versionProperties.load(FileInputStream(versionPropertiesFile))

    val versionMajor = versionProperties["VERSION_MAJOR"] as Int
    val versionMinor = versionProperties["VERSION_MINOR"] as Int
    var versionBuild = versionProperties["VERSION_BUILD"] as Int

    android {
        // increment 'versionBuild' in case a release is compiled
        val runTasks = gradle.startParameter.taskNames
        runTasks.forEach { task ->
            if (task.contains("assembleProRelease")) {
                // Update the build number in the local file
                versionBuild++
                versionProperties["VERSION_BUILD"] = versionBuild as String
                versionProperties.store(FileOutputStream(versionPropertiesFile), null)
            }
        }

        defaultConfig {
            println("versionBuild: $versionBuild")
            val versionCode = versionBuild
            val versionName = "${versionMajor}.${versionMinor}.${versionBuild}"
        }
    }
} else {
    throw GradleException("Could not read 'version.properties' from project root")
}