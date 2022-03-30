package com.madrex.edvora.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.madrex.edvora.R
import com.madrex.edvora.databinding.SplashScreenBinding
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
    lateinit var screenBinding: SplashScreenBinding
    private var ridesFetched = false
    private var userFetched = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenBinding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(screenBinding.root)
        (application as RideApplication).appComponent.inject(this)

        splashViewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
        screenBinding.progress.visibility = View.VISIBLE
        splashViewModel.ridesLD.observe(this, Observer {
            ridesFetched = true
            moveToRideScreen()
        })
        splashViewModel.userLD.observe(this, Observer {
            userFetched = true
            moveToRideScreen()
        })
    }
    private fun moveToRideScreen(){
        if(ridesFetched && userFetched) {
            screenBinding.progress.visibility = View.GONE
            val intent = Intent(this, RideScreen::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}