package com.example.flickrbrowse

import android.util.Log
import android.view.View
//import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar;

internal const val FLICKR_QUERY="FLICK_QUERY"
internal const val PHOTO_TRANSFER="PHOTO_TRANSFER"

open class BaseActivity : AppCompatActivity(){
    private val TAG="BaseActivity"
    internal fun activateToolBar(enableHome:Boolean){
        Log.d(TAG,"activateToolBar() called")
        var toolbar=findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}