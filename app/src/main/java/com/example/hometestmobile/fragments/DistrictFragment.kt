package com.example.hometestmobile.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hometestmobile.R
import com.example.hometestmobile.adapter.DistrictAdapter
import com.example.hometestmobile.customView.OnItemClick
import com.example.hometestmobile.models.District
import com.example.hometestmobile.models.Districts
import com.example.hometestmobile.network.SapoApi
import com.example.hometestmobile.utils.Constant
import com.example.hometestmobile.utils.Util
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors
import javax.inject.Inject

class DistrictFragment : DaggerFragment() {
    private lateinit var adapter: DistrictAdapter
    private lateinit var rcvDistrict: RecyclerView

    @Inject
    lateinit var sapoApi: SapoApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_district, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        initRecyclerView(view)
        callApi()
        return view
    }

    private fun initRecyclerView(view: View) {
        rcvDistrict = view.findViewById(R.id.rcv_city)
        rcvDistrict.layoutManager = LinearLayoutManager(activity)
        activity?.let {
            adapter = DistrictAdapter(it.applicationContext)
        }
        rcvDistrict.adapter = adapter
        adapter.onItemClick = object : OnItemClick {
            override fun onItemClickListener(position: Int) {
                adapter.districtList?.get(position)?.let { saveData(it) }
                openSexFragment()
            }
        }
    }

    private fun openSexFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_container, SexFragment.getInstance())
            ?.addToBackStack("DistrictFragment")
            ?.commit()
    }

    private fun callApi() {
        sapoApi.listDistrict()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    sortMap(it)
                },
                onError = { it.printStackTrace() }
            ).isDisposed
    }

    private fun saveData(district: District) {
        val pref =
            activity?.applicationContext?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val edit = pref?.edit()
        edit?.putString(Constant.REF_DISTRICT, district.name)
        edit?.apply()
    }

    private fun sortMap(districts: Districts) {
        val map: Map<String, List<District>> = districts.districts.stream()
            .collect(Collectors.groupingBy {
                it.cityCode.toString()
            })
        adapter.districtList = map[activity?.applicationContext?.let {
            Util.getPrefIntValue(it, Constant.REF_CITY_CODE)
        }.toString()] ?: error("Not success")
    }

    companion object {
        fun getInstance(): DistrictFragment {
            return DistrictFragment()
        }
    }
}