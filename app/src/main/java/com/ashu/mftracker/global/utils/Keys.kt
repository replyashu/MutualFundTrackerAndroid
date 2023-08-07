package com.ashu.mftracker.global.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun webClientKey(): String

    external fun notificationChannel(): String

    external fun notificationChannelDescription(): String

}