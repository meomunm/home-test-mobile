package com.example.hometestmobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.hometestmobile.R
import com.example.hometestmobile.utils.Constant
import com.example.hometestmobile.utils.Util

class GeneralFragment: Fragment() {
    private lateinit var generalUserName: TextView
    private lateinit var generalEmail: TextView
    private lateinit var generalAge: TextView
    private lateinit var generalSex: TextView
    private lateinit var generalCity: TextView
    private lateinit var generalDistrict: TextView

    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_general, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        initValue(view)
        return view
    }

    private fun initValue(view: View) {
        generalUserName = view.findViewById(R.id.general_username)
        generalEmail = view.findViewById(R.id.general_email)
        generalAge = view.findViewById(R.id.general_age)
        generalSex = view.findViewById(R.id.general_sex)
        generalCity = view.findViewById(R.id.general_city)
        generalDistrict = view.findViewById(R.id.general_district)

        btnNext = view.findViewById(R.id.btn_next)

        activity?.applicationContext?.let {
            generalUserName.text = Util.getPrefStringValue(it, Constant.REF_NAME)
            generalEmail.text = Util.getPrefStringValue(it, Constant.REF_EMAIL)
            generalAge.text = Util.getPrefIntValue(it,Constant.REF_AGE).toString()
            generalSex.text = Util.getPrefStringValue(it, Constant.REF_SEX)
            generalCity.text = Util.getPrefStringValue(it, Constant.REF_CITY)
            generalDistrict.text = Util.getPrefStringValue(it, Constant.REF_DISTRICT)
        }

        btnNext.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, FinishFragment.getInstance())
                ?.addToBackStack("GeneralFragment")
                ?.commit()
        }
    }

    companion object {
        fun getInstance() : GeneralFragment {
            return GeneralFragment()
        }
    }
}