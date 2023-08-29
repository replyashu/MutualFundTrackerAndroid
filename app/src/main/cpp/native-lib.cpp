//
// Created by Ashutosh Sharma on 07/08/23.
//
#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_ashu_mftracker_global_utils_Keys_webClientKey(JNIEnv *env, jobject thiz) {
    std::string web_client_key = "255790951127-ui9r080q6j9bn4l9jjm63vk8vrcv8v5g.apps.googleusercontent.com";
    return env->NewStringUTF(web_client_key.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ashu_mftracker_global_utils_Keys_notificationChannel(JNIEnv *env, jobject thiz) {
    std::string notification_channel = "mutual_fund_notification";
    return env->NewStringUTF(notification_channel.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_ashu_mftracker_global_utils_Keys_notificationChannelDescription(JNIEnv *env,jobject thiz) {
    std::string notification_channel_description = "this is notification channel for mutual fund tracker app";
    return env->NewStringUTF(notification_channel_description.c_str());
}