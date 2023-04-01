package com.example.flickrbrowse

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class FlickerImageViewHolder(view: View):RecyclerView.ViewHolder(view){
    var thumbnail:ImageView=view.findViewById(R.id.thumbnail)
    var title:TextView=view.findViewById(R.id.Title)
}

class flickrRecyclerViewAdapter(private var photoList: List<photo>) :RecyclerView.Adapter<FlickerImageViewHolder>(){
    private val TAG="FlickrRecyclerViewAdapt"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickerImageViewHolder {
        //This method is called by the layout manager when it needs a new View
        Log.d(TAG,"onCreateViewHolder new view requested")
        val view=LayoutInflater.from(parent.context).inflate(R.layout.browse, parent, false)
        return FlickerImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickerImageViewHolder, position: Int) {
        // This method is called by the layout manager when it needs new data in existing view
        val photoItem=photoList[position]
        Log.d(TAG,"onBindViewHolder is called")
        Glide.with(holder.thumbnail.context)
            .load(photoItem.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.thumbnail)
        holder.title.text = photoItem.title


    }

    override fun getItemCount(): Int {
        Log.d(TAG,"getItemCount called")
        return if(photoList.isNotEmpty()) photoList.size else 0
    }
    fun loadNewData(newPhotos:List<photo>){
        photoList=newPhotos
        notifyDataSetChanged()
    }
    fun getPhoto(position: Int):photo?{
        return if(photoList.isNotEmpty()) photoList[position] else null
    }
}