import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
}

android {
    namespace = "com.example.captureit.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.captureit.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        resValue("string", "app_name", "CaptureIt")
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
        }
        debug {
            resValue("string", "app_name", "CaptureIt Beta")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_17.target
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android)
}