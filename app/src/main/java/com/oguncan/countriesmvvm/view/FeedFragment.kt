package com.oguncan.countriesmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.oguncan.countriesmvvm.R
import com.oguncan.countriesmvvm.adapter.CountryAdapter
import com.oguncan.countriesmvvm.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * A simple [Fragment] subclass.
 */
class FeedFragment : Fragment() {
    private lateinit var viewModel : FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_feed, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        feedCountryList.layoutManager = LinearLayoutManager(context)
        feedCountryList.adapter = countryAdapter
        feedSwipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                feedCountryList.visibility = View.GONE
                txtFeedCountryError.visibility = View.GONE
                progressBarFeedCountry.visibility = View.VISIBLE
                viewModel.refreshFromAPI()
                feedSwipeRefreshLayout.isRefreshing = false
            }

        })
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                feedCountryList.visibility=View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    txtFeedCountryError.visibility = View.VISIBLE
                    feedCountryList.visibility = View.GONE
                    progressBarFeedCountry.visibility = View.GONE
                }
                else{
                    txtFeedCountryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let{
                if(it){
                    progressBarFeedCountry.visibility = View.VISIBLE
                    feedCountryList.visibility = View.GONE
                    txtFeedCountryError.visibility = View.GONE
                }
                else{
                    progressBarFeedCountry.visibility = View.GONE

                }
            }
        })
    }

}
