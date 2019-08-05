val androidGradlePluginVersion: String by settings
val androidMavenPublishPluginVersion: String by settings
val kotlinVersion: String by settings

pluginManagement {

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.library" ->
                    useModule("com.android.tools.build:gradle:$androidGradlePluginVersion")
                "org.jetbrains.kotlin.android", "org.jetbrains.kotlin.android.extensions" ->
                    useModule(kotlin("gradle-plugin", kotlinVersion))
                "digital.wup.android-maven-publish" ->
                    useModule("digital.wup:android-maven-publish:$androidMavenPublishPluginVersion")
            }
        }
    }
}

fun PluginResolveDetails.kotlin(module: String, version: String? = null) =
    "org.jetbrains.kotlin:kotlin-$module${version?.let { ":$version" } ?: ""}"

rootProject.name = "dynamic-spacer-decorator"

