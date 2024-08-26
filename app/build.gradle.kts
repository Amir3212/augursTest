plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("org.sonarqube")
}

android {
    namespace = "com.inerviewapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.inerviewapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


    lint {
        htmlReport = true
        htmlOutput = file("build/reports/lint-results.html")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //Retrofit
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Glide
    implementation(libs.glide)

    //ePoxy
    implementation(libs.epoxy)
    implementation(libs.epoxy.paging3)
    implementation(libs.epoxy.paging)

    implementation(libs.circleimageview)

    implementation(libs.androidx.room.ktx)
    implementation(libs.easypermissions)


    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)


    //Room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}

kapt {
    correctErrorTypes = true
}