plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.tamerlan.cryptoapp"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.tamerlan.cryptoapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}
//kotlin {
//    jvmToolchain(8)
//}


dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.cardview:cardview:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")



    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")


    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    val lifecycle_version = "2.8.7"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

//    //noinspection LifecycleAnnotationProcessorWithJava8
//    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")


    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.work:work-runtime-ktx:2.10.0")

//    implementation(libs.dagger)
//    ksp(libs.dagger.compiler)
//    ksp(libs.dagger.android.processor)

    implementation("com.google.dagger:dagger:2.52")
    ksp("com.google.dagger:dagger-android-processor:2.52")
    ksp("com.google.dagger:dagger-compiler:2.52")


}