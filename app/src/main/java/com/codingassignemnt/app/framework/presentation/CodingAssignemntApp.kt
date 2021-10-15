package com.codingassignemnt.app.framework.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CodingAssignemntApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}