package com.madrex.edvora.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.madrex.edvora.model.User
import javax.inject.Inject

class Preferences @Inject constructor(private val sharedPreferences: SharedPreferences){
    fun saveUser(user: User){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("user", Gson().toJson(user))
        editor.apply()
    }
    fun getUser():User{
        return Gson().fromJson(sharedPreferences.getString("user",""), User::class.java)
    }
}