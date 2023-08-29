package com.ashu.mftracker.api.user

import com.ashu.mftracker.data.requests.NotificationToken
import com.ashu.mftracker.data.requests.RegisterUser
import com.ashu.mftracker.data.response.RegisterResponse
import com.ashu.mftracker.data.user.ProfileUser
import retrofit2.Response

interface UserApiHelper {

    suspend fun registerNewUser(registerUser: RegisterUser): Response<RegisterResponse>

    suspend fun updateNotificationToken(notificationToken: NotificationToken): Response<Boolean>

    suspend fun sendNotificationToAll(): Response<Boolean>

    suspend fun getUserProfileData(userId: String?): Response<ProfileUser>

}