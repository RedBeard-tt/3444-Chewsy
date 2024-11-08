buildscript {
    val composeVersion by extra("1.4.3") // Update to the latest Compose version
    val navVersion by extra("2.6.0")     // Update to the latest navigation version

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1") // Update to the latest Gradle version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0") // Update Kotlin version
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath("com.google.gms:google-services:4.3.15") // Update Google Services version if needed
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}