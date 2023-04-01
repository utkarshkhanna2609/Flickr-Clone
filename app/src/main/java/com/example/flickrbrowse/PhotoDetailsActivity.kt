package com.example.flickrbrowse

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PhotoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)

        val photo = intent.getParcelableExtra<photo>(PHOTO_TRANSFER) as photo

        val pd = findViewById<TextView>(R.id.photo_title)
        val pt = findViewById<TextView>(R.id.photo_tags)
        val au = findViewById<TextView>(R.id.photo_author)
        val img = findViewById<ImageView>(R.id.imageView)

        pd.text = photo.title
        pt.text = photo.tags
        au.text = photo.author

        Glide.with(this)
            .load(photo.link)
            .error(R.drawable.ic_image)
            .placeholder(R.drawable.ic_image)
            .into(img)
    }
}
