package com.example.hometestmobile.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.hometestmobile.R
import com.example.hometestmobile.utils.Constant
import com.example.hometestmobile.utils.Util
import kotlinx.android.synthetic.main.login_sapo_fragment.*

class LoginFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.login_sapo_fragment, container, false)
        initView(view)
        return view
    }

    companion object {
        fun getInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    private fun initView(view: View) {
        val btnNext: Button = view.findViewById(R.id.btn_next)
        btnNext.setOnClickListener(this)

        val mainlayout = view.findViewById<ConstraintLayout>(R.id.mainLayout)
        mainlayout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                activity?.let { Util.hideSoftKeyboard(it) }
                return false
            }
        })
    }

    private fun confirmData() {
        var isValidData = true
        if (Util.isValidName(edt_username.text.toString())) {
            input_layout_username.isErrorEnabled = false
        } else {
            isValidData = false
            input_layout_username.error = getString(R.string.msg_error_username)
        }

        if (Util.isValidEmail(edt_email.text.toString())) {
            input_layout_email.isErrorEnabled = false
        } else {
            isValidData = false
            input_layout_email.error = getString(R.string.msg_error_email)
        }

        if (Util.isValidPassword(edt_password.text.toString())) {
            input_layout_password.isErrorEnabled = false
        } else {
            isValidData = false
            input_layout_password.error = getString(R.string.msg_error_password)
        }

        if (Util.isValidRepeatPassword(
                edt_password.text.toString(),
                edt_repeat_password.text.toString()
            )
        ) {
            input_layout_repeat_password.isErrorEnabled = false
        } else {
            isValidData = false
            input_layout_repeat_password.error = getString(R.string.msg_error_repeat_password)
        }

        openCitiesFragment(isValidData)
    }

    private fun saveData() {
         val pref = activity?.applicationContext?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
         val edit = pref?.edit()

        edit?.putString(Constant.REF_NAME, edt_username.text.toString())
        edit?.putString(Constant.REF_EMAIL, edt_email.text.toString())
        edit?.apply()
    }

    private fun openCitiesFragment(isValid: Boolean) {
        if (isValid) {
            saveData()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, CitiesFragment.getInstance())
                ?.addToBackStack("LoginFragment")
                ?.commit()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_next -> {
                confirmData()
            }
        }
    }
}