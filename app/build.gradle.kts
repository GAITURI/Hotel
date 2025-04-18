


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.parcelize)

}

android {
    namespace = "com.example.hotel"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hotel"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        dataBinding = true  // ✅ Use dataBinding instead of viewBinding (if needed)
        viewBinding= true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // UI & Network
    implementation(libs.floatingsearchview)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.glide)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation(libs.volley)
    implementation(libs.firebase.database.ktx)
    annotationProcessor("androidx.room:room-compiler:2.6.1")


    // Firebase
    implementation(libs.firebase.auth)

    // UI Extras
    implementation(libs.circleimageview)
    implementation(libs.android.gif.drawable)
    implementation (libs.lottie)

    // Lifecycle & ViewModel
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)

    // ✅ Explicitly add Data Binding Runtime (tovoid version mismatch)
    implementation(libs.androidx.databinding.runtime)

    // Testing a
    implementation(libs.androidx.runner)
    implementation(libs.car.ui.lib)
    implementation(libs.picasso)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// ✅ Force consistent versions to avoid duplicates
configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "androidx.databinding" && requested.name == "baseLibrary") {
                // Exclude baseLibrary from all dependencies
                exclude(group = "androidx.databinding", module = "baseLibrary")
                        when (requested.name) {
                            "databinding-common" -> useVersion("8.8.0")
                            "databinding-runtime" -> useVersion("8.8.0")
                            "databinding-compiler" -> useVersion("8.8.0")

                        }


            }

        }
    }
}