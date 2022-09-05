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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    /*-----------Thread Dependencies---------*/
    api(Libs.kotlinSerializationJson)
    /*-----------Network Dependencies---------*/
    implementation(Libs.okhttp)
    implementation(Libs.converterGson)
    /*-----------Thread Dependencies---------*/
    implementation(Libs.coroutinesAndroid)
    implementation(Libs.coroutinesCore)
    implementation(Libs.hiltAndroid)
    kapt(Libs.hiltDaggerCompiler)
    /*-----------Room Dependencies---------*/
    implementation(Libs.roomKtx)
    implementation(Libs.roomRuntime)
    kapt(Libs.roomCompiler)
}