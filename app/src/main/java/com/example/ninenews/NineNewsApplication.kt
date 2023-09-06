package com.example.ninenews

import android.app.Application
import com.example.ninenews.data.AppContainer
import com.example.ninenews.data.DefaultAppContainer

//defined in manifest,
// so the app uses this application class
class NineNewsApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}