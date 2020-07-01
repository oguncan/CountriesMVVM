package com.oguncan.countriesmvvm.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguncan.countriesmvvm.R
import com.oguncan.countriesmvvm.databinding.RowListCountryBinding
import com.oguncan.countriesmvvm.model.Country
import com.oguncan.countriesmvvm.util.downloadFromURL
import com.oguncan.countriesmvvm.util.placeHolderProgressBar
import com.oguncan.countriesmvvm.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.row_list_country.view.*

class CountryAdapter(val countryList : ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view : RowListCountryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        //var view = inflater.inflate(R.layout.row_list_country, parent, false)
        var view = DataBindingUtil.inflate<RowListCountryBinding>(inflater, R.layout.row_list_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList.get(position)
        holder.view.listener = this


        //holder.itemView.txtFeedCountryName.text = countryList[position].countryName
        //holder.itemView.txtFeedCountryRegion.text = countryList[position].countryRegion
//        holder.itemView.imgViewCountryFlag.downloadFromURL(countryList.get(position).countryImageURL, placeHolderProgressBar(holder.itemView.context))
//
//        holder.itemView.setOnClickListener(
//            object: View.OnClickListener{
//                override fun onClick(p0: View?) {
//                    val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(countryUuid = countryList[position].uuid)
//                    Navigation.findNavController(p0!!).navigate(action)
//                }
//            }
//        )

    }


    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClick(v: View) {
        val uuid = v.txtCountryUuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(countryUuid = uuid)
        Navigation.findNavController(v!!).navigate(action)
    }


}