plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android") // Плагин Hilt
    kotlin("kapt")
    alias(libs.plugins.kotlin.compose) // добавляем плагин для ObjectBox
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("dagger.fastInit", "enabled")
        arg("kapt.kotlin.generated", "true")
    }
}


android {
    namespace = "com.hse.hseproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hse.hseproject"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose=true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    // ObjectBox
    implementation(libs.objectbox.android)
    implementation(libs.objectbox.kotlin)
    kapt(libs.objectbox.processor)

    //lottie animation
    implementation (libs.lottie.compose)

    // Hilt & DI
    implementation(libs.hilt.android.v2511)
    kapt(libs.hilt.android.compiler.v2511)
    implementation(libs.androidx.hilt.navigation.compose)

    // UI
    implementation(libs.androidx.material3)  // use Material 3 like main
    implementation(libs.material)
    implementation(libs.coil.compose)
    runtimeOnly(libs.accompanist.systemuicontroller) //for status bar


    // https://mvnrepository.com/artifact/io.coil-kt.coil3/coil-network-okhttp
    runtimeOnly(libs.coil.network.okhttp)
    implementation(libs.relinker)

    // Networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.fragment.ktx)

    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM (управляет версиями Compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    androidTestImplementation(platform(libs.androidx.compose.bom))

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}