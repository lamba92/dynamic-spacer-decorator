import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    id("digital.wup.android-maven-publish")
}

group = "com.github.lamba92"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
    jcenter()
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
        versionCode = 1
        versionName = version as String
    }
}

dependencies {

    val androidXVersion: String by project
    val kotlinVersion: String by project

    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    api("androidx.recyclerview", "recyclerview", androidXVersion)

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}

publishing {
    publications {
        create<MavenPublication>("mavenAar") {
            from(components["android"])
            artifact(sourcesJar)
        }
    }

}