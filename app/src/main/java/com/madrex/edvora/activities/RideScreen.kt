package com.madrex.edvora.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.madrex.edvora.R
import com.madrex.edvora.databinding.RideDetailBinding
import com.madrex.edvora.databinding.RideScreenBinding
import com.madrex.edvora.model.Ride
import com.madrex.edvora.model.User
import com.madrex.edvora.utils.Preferences
import com.madrex.edvora.viewModels.RideViewModel
import com.madrex.edvora.viewModels.SplashViewModel
import com.madrex.edvora.viewModels.ViewModelFactory
import javax.inject.Inject

class RideScreen : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var preferences: Preferences

    lateinit var rideViewModel: RideViewModel

    lateinit var rideScreenBinding : RideScreenBinding

    lateinit var rideAdapter: RideAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rideScreenBinding = RideScreenBinding.inflate(layoutInflater)
        setContentView(rideScreenBinding.root)

        (application as RideApplication).appComponent.inject(this)

        rideViewModel = ViewModelProvider(this, viewModelFactory)[RideViewModel::class.java]

        rideScreenBinding.user = preferences.getUser()

        rideScreenBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> rideViewModel.getNearest(preferences.getUser())
                    1 -> rideViewModel.getUpcoming()
                    2 -> rideViewModel.getPast()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rideScreenBinding.ridesRecycler.layoutManager = layoutManager
        rideAdapter = RideAdapter(listOf(),preferences.getUser())
        rideScreenBinding.ridesRecycler.adapter = rideAdapter

        rideViewModel.ridesLD.observe(this, Observer {
//            rideScreenBinding.rideList = it
//            rideAdapter.update(it)
            rideViewModel.getNearest(preferences.getUser())
        })
        rideViewModel.filteredLD.observe(this, Observer {
            rideScreenBinding.rideList = it
            rideAdapter.update(it)
        })

        rideScreenBinding.filter.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext,"Unimplemented",Toast.LENGTH_LONG).show()
        })

    }
    class RideAdapter(private var rideList: List<Ride>,private val user: User) :
        RecyclerView.Adapter<RideAdapter.RideHolder>() {
        inner class RideHolder(val binding: RideDetailBinding) : RecyclerView.ViewHolder(binding.root)
        @NonNull
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideHolder {
            val binding = RideDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return RideHolder(binding)
        }
        override fun onBindViewHolder(holder: RideHolder, position: Int) {
            holder.binding.ride = rideList[position]
            holder.binding.user = user
        }
        override fun getItemCount(): Int {
            return rideList.size
        }
        fun update(rideList: List<Ride>) {
            this.rideList = rideList
            this.notifyDataSetChanged()
        }
    }
}