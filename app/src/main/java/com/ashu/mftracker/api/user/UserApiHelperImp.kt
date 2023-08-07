package com.ashu.mftracker.api.user

import com.ashu.mftracker.data.requests.NotificationToken
import com.ashu.mftracker.data.requests.RegisterUser
import com.ashu.mftracker.data.response.RegisterResponse
import com.ashu.mftracker.data.user.ProfileUser
import retrofit2.Response
import javax.inject.Inject

class UserApiHelperImp @Inject constructor(private val userApiService: UserApiService): UserAPiHelper {

    override suspend fun registerNewUser(registerUser: RegisterUser): Response<RegisterResponse> =
        userApiService.registerUser(registerUser)

    override suspend fun updateNotificationToken(notificationToken: NotificationToken):
            Response<Boolean> = userApiService.updateNotificationToken(notificationToken)

    override suspend fun sendNotificationToAll(): Response<Boolean> =
        userApiService.sendNotificationToAll()

    override suspend fun getUserProfileData(userId: String?): Response<ProfileUser> =
        userApiService.fetchUserProfile(userId)

}