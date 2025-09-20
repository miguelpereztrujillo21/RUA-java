plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
}
hilt {
    enableAggregatingTask = false
}
android {
    namespace = "com.mpt.rua_java"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mpt.rua_java"
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
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.core.ktx)

    // Core Android
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)

    // Networking - Retrofit + OkHttp + Gson
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

    // Database - Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Dependency Injection - Hilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)

    // Lifecycle & ViewModel
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // QR Code Generation - ZXing
    implementation(libs.zxing.core)
    implementation(libs.zxing.android)

    // Image Loading - Glide & CircleImageView
    implementation(libs.glide)
    implementation(libs.circleimageview)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.junit5.api)
    testImplementation(libs.junit5.engine)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.truth)
    testImplementation(libs.mockwebserver)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}