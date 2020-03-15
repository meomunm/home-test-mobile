package com.example.hometestmobile.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hometestmobile.R
import com.example.hometestmobile.adapter.CitiesAdapter
import com.example.hometestmobile.customView.OnItemClick
import com.example.hometestmobile.models.City
import com.example.hometestmobile.network.SapoApi
import com.example.hometestmobile.utils.Constant
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CitiesFragment : DaggerFragment() {
    private lateinit var adapter: CitiesAdapter
    private lateinit var rcvCity: RecyclerView

    @Inject
    lateinit var sapoApi: SapoApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cities, container, false)
        initRecyclerView(view)
        callApi()
        return view
    }

    private fun initRecyclerView(view: View) {
        rcvCity = view.findViewById(R.id.rcv_city)
        rcvCity.layoutManager = LinearLayoutManager(activity)
        activity?.applicationContext?.let {
            adapter = CitiesAdapter(it.applicationContext)
        }
        rcvCity.adapter = adapter
        adapter.onItemClick = object : OnItemClick {
            override fun onItemClickListener(position: Int) {
                adapter.cityList?.cities?.get(position)?.let { saveData(it) }
                openDistrictFragment()
            }
        }
    }

    private fun openDistrictFragment() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fl_container, DistrictFragment.getInstance())
            ?.addToBackStack("CitiesFragment")
            ?.commit()
    }

    private fun saveData(city: City) {
        val pref = activity?.applicationContext?.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
        val edit = pref?.edit()

        edit?.putString(Constant.REF_CITY, city.name)
        city.cityCode?.let { edit?.putInt(Constant.REF_CITY_CODE, it) }
        edit?.apply()
        Log.d("MyDebugTag", "CitiesFragment - saveData(): done")
    }
    private fun callApi() {
        sapoApi.listCity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    adapter.cityList = it
                },
                onError = { it.printStackTrace() }
            ).isDisposed
    }

    companion object {
        fun getInstance(): CitiesFragment {
            return CitiesFragment()
        }
    }
}