plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.awesomepetprojects.seeyou"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.awesomepetprojects.seeyou"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "WEB_CLIENT_ID", project.properties["WEB_CLIENT_ID"].toString())

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase Bom
    implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    // Firebase analytics
    implementation("com.google.firebase:firebase-analytics")

    // Firebase auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // Koin
    implementation("io.insert-koin:koin-android:3.5.3")

    // Google Play services
    implementation("com.google.android.gms:play-services-auth:21.0.0")
}