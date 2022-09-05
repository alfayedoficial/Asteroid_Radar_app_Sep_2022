#include <jni.h>
#include <string>
#include <iostream>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_alfayedoficial_coreNetwork_core_di_UrlDomain_provideUrlDomainOfDeployment(JNIEnv *env,jobject thiz) {
    std::string baseURL = "https://api.nasa.gov";
    return env->NewStringUTF(baseURL.c_str());
}




extern "C"
JNIEXPORT jstring JNICALL
Java_com_alfayedoficial_coreNetwork_core_di_UrlDomain_provideApiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "sutLgyg8qA2FHcZFGTb4HTkfOmogeFjTxHLV1onN";
    return env->NewStringUTF(apiKey.c_str());
}