package com.example.hometestmobile.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.RadioButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.hometestmobile.R
import com.example.hometestmobile.utils.Constant
import kotlinx.android.synthetic.main.fragment_sex.*

class SexFragment : Fragment() {
    private lateinit var numberPicker: NumberPicker
    private lateinit var btnNext: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sex, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        initView(view)
        return view
    }

    private fun initView(view: View) {
        numberPicker = view.findViewById(R.id.nbp_picker_age)
        btnNext = view.findViewById(R.id.btn_next)
        numberPicker.minValue = 8
        numberPicker.maxValue = 80
        numberPicker.value = 25

        btnNext.setOnClickListener {
            saveData()
            openGeneralFragment()
        }
    }

    companion object {
        fun getInstance(): SexFragment {
            return SexFragment()
        }
    }

    private fun openGeneralFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_container, GeneralFragment.getInstance())
            ?.addToBackStack("SexFragment")
            ?.commit()
    }

    private fun saveData() {
        val pref =
            activity?.applicationContext?.getSharedPreferences(
                Constant.NAME_PREF,
                Context.MODE_PRIVATE
            )
        val edit = pref?.edit()
        val radioButton = radio_group.findViewById<RadioButton>(radio_group.checkedRadioButtonId)
        edit?.putInt(Constant.REF_AGE, numberPicker.value)

        edit?.putString(Constant.REF_SEX, radioButton.text.toString())
        edit?.apply()
    }
}