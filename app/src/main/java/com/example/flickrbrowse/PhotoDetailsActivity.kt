package com.example.flickrbrowse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class PhotoDetailsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        //activateToolBar(true)
        val photo=intent.getSerializableExtra(PHOTO_TRANSFER) as photo
        val pd=findViewById<TextView>(R.id.photo_title)
        val pt=findViewById<TextView>(R.id.photo_tags)
        val au=findViewById<TextView>(R.id.photo_author)
        val img=findViewById<ImageView>(R.id.imageView)
        pd.text=photo.title
        pt.text=photo.tags
        au.text=photo.author

        Glide.with(this)
            .load(photo.link)
            .error(R.drawable.ic_image)
            .placeholder(R.drawable.ic_image)
            .into(img)


    }
}