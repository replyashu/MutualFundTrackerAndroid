package com.ashu.mftracker.api.user

import com.ashu.mftracker.data.requests.NotificationToken
import com.ashu.mftracker.data.requests.RegisterUser
import com.ashu.mftracker.data.response.RegisterResponse
import com.ashu.mftracker.data.user.ProfileUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApiService {

    @POST("user/save")
    suspend fun registerUser(@Body registerUser: RegisterUser): Response<RegisterResponse>

    @POST("user/enable-push")
    suspend fun updateNotificationToken(@Body notificationToken: NotificationToken): Response<Boolean>

    @POST("user/notify")
    suspend fun sendNotificationToAll(): Response<Boolean>

    @GET("user/profile")
    suspend fun fetchUserProfile(@Query("userId") userId: String?): Response<ProfileUser>

}