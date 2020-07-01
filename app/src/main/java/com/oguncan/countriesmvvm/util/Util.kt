package com.oguncan.countriesmvvm.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.oguncan.countriesmvvm.R

//Extension Function

//fun String.myExtension(myParameter : String){
//    println(myParameter)
//}

fun ImageView.downloadFromURL(url : String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
    }
}
@BindingAdapter("android:downloadURL")
fun downloadImage(view : ImageView, url: String?){
    view.downloadFromURL(url, placeHolderProgressBar(view.context))
}