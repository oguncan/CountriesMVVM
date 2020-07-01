package com.oguncan.countriesmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.oguncan.countriesmvvm.view.DetailFragmentArgs
import com.oguncan.countriesmvvm.R
import com.oguncan.countriesmvvm.databinding.FragmentDetailBinding
import com.oguncan.countriesmvvm.util.downloadFromURL
import com.oguncan.countriesmvvm.util.placeHolderProgressBar
import com.oguncan.countriesmvvm.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel : DetailViewModel
    private lateinit var dataBinding : FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            countryUuid = DetailFragmentArgs.fromBundle(
                it!!
            ).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
        observeLiveData()


    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let{
                dataBinding.selectedCountry = it

//                context.let {con ->
//                    imgViewDetailCountryFlag.downloadFromURL(it.countryImageURL, placeHolderProgressBar(con!!))
//                }

            }
        })
    }

}
