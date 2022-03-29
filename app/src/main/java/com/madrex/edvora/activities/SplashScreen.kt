package com.madrex.edvora.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.madrex.edvora.R
import com.madrex.edvora.utils.Preferences
import com.madrex.edvora.viewModels.SplashViewModel
import com.madrex.edvora.viewModels.ViewModelFactory
import javax.inject.Inject

class SplashScreen : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var preferences: Preferences

    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        (application as RideApplication).appComponent.inject(this)

        splashViewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)

        splashViewModel.ridesLD.observe(this, Observer {
            Toast.makeText(applicationContext,"",Toast.LENGTH_LONG)
        })
    }
}