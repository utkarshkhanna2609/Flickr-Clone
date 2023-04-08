package com.example.flickrbrowse

import android.app.DownloadManager
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.flickrbrowse.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {
    private val TAG="SearchActivity"
    private var searchView: SearchView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,".onCreate starts")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Log.d(TAG,".onCreate ends")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(TAG,".onCreateOptionsMenu: Starts")
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView

        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchableInfo)
        searchView?.isIconified = false

        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG,"onQueryTextSubmit called")
                val sharedPref=PreferenceManager.getDefaultSharedPreferences(applicationContext)
                sharedPref.edit().putString(FLICKR_QUERY,query).apply()
                finish()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
       })

        return true
    }

}
