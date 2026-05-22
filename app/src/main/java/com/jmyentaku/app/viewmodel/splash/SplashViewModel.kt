package com.jmyentaku.app.viewmodel.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SplashViewModel : ViewModel() {

    fun isUserLogged(): Boolean {

        return FirebaseAuth
            .getInstance()
            .currentUser != null
    }
}