package com.example.flickrbrowse

import android.graphics.Bitmap
import android.os.AsyncTask
import android.provider.ContactsContract
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class GetFlickrJsonData(private val listener: OnDataAvailable):
    AsyncTask<String, Void, ArrayList<photo>>() {
    private val TAG="GetFlickrJsonData"
    interface OnDataAvailable{
        fun onDataAvailable(data:List<photo>)
        fun onError(exception: Exception)}


    override fun doInBackground(vararg params: String?): ArrayList<photo> {
        Log.d(TAG, "doInBackground strt")

        val photoList=ArrayList<photo>()
        try{
            val jsonData= JSONObject(params[0])
            val itemsArray=jsonData.getJSONArray("items")
            for(i in 0 until itemsArray.length()){
                val jsonPhoto=itemsArray.getJSONObject(i)
                val title=jsonPhoto.getString("title")
                val author=jsonPhoto.getString("author")
                val authorId=jsonPhoto.getString("author_id")
                val tags=jsonPhoto.getString("tags")
                val jsonMedia=jsonPhoto.getJSONObject("media")
                val photoUrl=jsonMedia.getString("m")
                val link=photoUrl.replaceFirst("_m.jpg","_b.jpg")

                val photoObject=photo(title, author, authorId, link, tags, photoUrl)
                photoList.add(photoObject)
                Log.d(TAG, ".doInbackground $photoObject")
            }
        }catch(e: JSONException){
            e.printStackTrace()
            Log.e(TAG, ".doInBackground: Error Processing Json Data. ${e.message}")
            cancel(true)

            listener.onError(e)
        }
        Log.d(TAG,".doInBackground Ends")
        return photoList
    }


    override fun onPostExecute(result: ArrayList<photo>) {
        Log.d(TAG, "onPostExecute strt")
        super.onPostExecute(result)
        listener.onDataAvailable(result)
        Log.d(TAG, "onPostExecute ends")
    }
}