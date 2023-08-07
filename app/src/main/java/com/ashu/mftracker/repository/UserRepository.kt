package com.ashu.mftracker.repository

import com.ashu.mftracker.api.user.UserAPiHelper
import com.ashu.mftracker.data.requests.NotificationToken
import com.ashu.mftracker.data.requests.RegisterUser
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPiHelper: UserAPiHelper) {

    suspend fun registerUser(registerUser: RegisterUser) = userAPiHelper.registerNewUser(registerUser)

    suspend fun updateNotificationToken(notificationToken: NotificationToken) =
        userAPiHelper.updateNotificationToken(notificationToken)

    suspend fun sendNotifications() = userAPiHelper.sendNotificationToAll()

    suspend fun fetchUserData(userId: String?) = userAPiHelper.getUserProfileData(userId)


}