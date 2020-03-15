package com.example.hometestmobile.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.util.regex.Matcher
import java.util.regex.Pattern

class Util {
    companion object {
        fun isValidName(name: String): Boolean {
            if (name.length < 8) {
                return false
            }
            return true
        }

        fun isValidEmail(email: String): Boolean {
            val regEmail: String = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" +
                    "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                    "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                    "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                    "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" +
                    "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
            val inputStr: CharSequence = email
            val pattern: Pattern = Pattern.compile(regEmail, Pattern.CASE_INSENSITIVE)
            val matcher: Matcher = pattern.matcher(inputStr)

            if (matcher.matches()) {
                return true
            }
            return false
        }

        fun isValidPassword(password: String): Boolean {
            val regPassword = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$"
            val inputStr: CharSequence = password
            val pattern: Pattern = Pattern.compile(regPassword)
            val matcher: Matcher = pattern.matcher(inputStr)

            if (matcher.matches()) {
                return true
            }
            return false
        }

        fun isValidRepeatPassword(password: String, passwordRecent: String): Boolean {
            if (password.equals(passwordRecent)) {
                return true
            }
            return false
        }

        fun getPrefIntValue(applicationContext: Context, key: String) : Int{
            val pref = applicationContext.getSharedPreferences(Constant.NAME_PREF, Context.MODE_PRIVATE)
            return pref.getInt(key, Constant.DEFAULT_INT)
        }

        fun getPrefStringValue(applicationContext: Context, key: String) : String? {
            val pref = applicationContext.getSharedPreferences(Constant.NAME_PREF, Context.MODE_PRIVATE)
            return  pref.getString(key, Constant.DEFAULT_STRING)
        }

        fun hideSoftKeyboard(activity: Activity) {
            if  (activity.currentFocus == null) {
                return
            }
            val inputMethodManager: InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }
}