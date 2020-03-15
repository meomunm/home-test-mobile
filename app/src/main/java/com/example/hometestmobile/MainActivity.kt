package com.example.hometestmobile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hometestmobile.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, LoginFragment.getInstance())
            .commit()
    }

    override fun onStop() {
        val pref =
            this.applicationContext?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val edit = pref?.edit()?.clear()
        edit?.apply()
        super.onStop()
    }
}
