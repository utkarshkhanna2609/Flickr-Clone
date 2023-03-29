package com.example.flickrbrowse

import android.content.ContentValues.TAG
import android.net.Uri
import android.nfc.NdefRecord.createUri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.flickrbrowse.databinding.ActivityMainBinding
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity(), getRawData.onDownloadComplete, GetFlickrJsonData.OnDataAvailable {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val url=createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo","en-us",true)
        val getRawData=getRawData(this)
        //getRawData.setDownloadCompleteListener(this)
        getRawData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=android,oreo&format=json&nojsoncallback=1")

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun createUri(baseURL: String, searchCriteria:String, lang:String,matchAll:Boolean):String{
        Log.d(TAG,"createUri starts")
        var uri= Uri.parse(baseURL)
            .buildUpon().
            appendQueryParameter("tags",searchCriteria).
            appendQueryParameter("tagmode",if(matchAll) "ALL" else "ANY").
            appendQueryParameter("lang",lang).
            appendQueryParameter("format","json").
            appendQueryParameter("nojsoncallback","1").build()
        return uri.toString()
        Log.d(TAG,"createUri ends")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if(status==DownloadStatus.OK)
        {
            Log.d(TAG,"onDownloadCompleteCalled")
            val getFlickrJsonData=GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
            //getFlickrJsonData.execute("bogus data")
        } else{
            Log.d(TAG,"failed to call onDownloadComplete failed with status $status. Error message is: $data")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDataAvailable(data: List<photo>) {
        Log.d(TAG,"onDataAvailabale called, data is $data")
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError called, with ${exception.message}")
    }
}