package com.ashu.mftracker.ui.login

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ashu.mftracker.data.requests.NotificationToken
import com.ashu.mftracker.databinding.FragmentLoginBinding
import com.ashu.mftracker.global.MainActivity
import com.ashu.mftracker.global.clickWithDebounce
import com.ashu.mftracker.global.network.Status
import com.ashu.mftracker.global.utils.Keys
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity

    private val viewModel by viewModels<LoginViewModel>()

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    private val sharedPreferences: SharedPreferences by lazy { getSharedPreferences("preference_key", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = viewModel.getSigninRequest(Keys.webClientKey())

        binding?.googleButton?.clickWithDebounce {
            googleSignIn()
        }

        viewModel.register.observe(this) {
            when(it.status) {
                Status.ALREADY_REGISTERED -> {
                    Toast.makeText(this, it.message + "  " + it.data, Toast.LENGTH_LONG).show()
                    sharedPreferences.edit().putString("user_uuid", it.data?.userUid).apply()
                    retrieveFirebaseNotificationToken(it.data?.userUid)
                    showDashBoard()
                }
                Status.SUCCESS -> {
                    Toast.makeText(this, "Welcome" + it.data?.name, Toast.LENGTH_LONG).show()
                    sharedPreferences.edit().putString("user_uuid", it.data?.userUid).apply()
                    retrieveFirebaseNotificationToken(it.data?.userUid)
                    showDashBoard()
                }
                Status.ERROR ->
                    Toast.makeText(this, "Some error" + it.message, Toast.LENGTH_LONG).show()
                else -> {

                }
            }
        }
    }

    private fun googleSignIn() {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0, null)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("Login1", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                e.localizedMessage?.let { Log.d("Login2", it) }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    viewModel.loginVerification(oneTapClient, data)
                } catch (e: ApiException) {
                    viewModel.handleLoginException(e)
                }
            }
        }
    }

    private fun showDashBoard() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun retrieveFirebaseNotificationToken(userId: String?) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("message token", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            sharedPreferences.edit().putString("user_token", token).apply()
            viewModel.updateToken(NotificationToken(userId, token.toString()))
            // Log and toast
            Log.d("Token", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onClick(v: View?) {

    }


}