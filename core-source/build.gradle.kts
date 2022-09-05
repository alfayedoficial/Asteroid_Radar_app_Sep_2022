plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Android.compileSDK

    defaultConfig {
        minSdk = Android.minSDK
        targetSdk = Android.compileSDK
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
        multiDexEnabled = true
        consumerProguardFiles()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        getByName("debug") {
            multiDexEnabled = true
        }

        getByName("release") {
            multiDexEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.18.1"
        }
    }


}

dependencies {

    api(project(":core-model"))

    implementation(Libs.multidex)
    /*-----------Network Dependencies---------*/
    implementation(Libs.retrofit)
    implementation(Libs.converterMoshi)
    implementation(Libs.converterGson)
    implementation(Libs.gson)
    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)
    implementation(Libs.chuck)
    api(Libs.kotlinSerializationJson)
    /*-----------Thread Dependencies---------*/
    implementation(Libs.coroutinesAndroid)
    implementation(Libs.coroutinesCore)
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltDaggerCompiler)
    /*-----------Github Tools Dependencies---------*/
    implementation(Libs.kotlinUtils)
    /*-----------Room Dependencies---------*/
    implementation(Libs.roomKtx)
    implementation(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
}