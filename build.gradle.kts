buildscript {
    dependencies {
        classpath(libs.gms.google.services)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
        classpath ("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.1.1")

    }
}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false

    id("com.android.library") version "7.2.1" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.16" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}