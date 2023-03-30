package com.example.flickrbrowse

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

enum class DownloadStatus{
    OK, IDLE, NOT_INITIALIZED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}



class getRawData(private val listener: onDownloadComplete): AsyncTask<String, Void, String>() {
    private val TAG="getRawData"
    private var downloadStatus=DownloadStatus.IDLE
    //private var listener:MainActivity? = null

    /*fun setDownloadCompleteListener(callbackObject:MainActivity){
        listener=callbackObject
    }*/

    interface onDownloadComplete{
        fun onDownloadComplete(data:String, status: DownloadStatus)
    }

    override fun onPostExecute(result: String) {
        Log.d(TAG, "onPostExecute called")
        listener.onDownloadComplete(result, downloadStatus)

    }
    override fun doInBackground(vararg params: String?): String {

        if(params[0]==null){
            downloadStatus=DownloadStatus.NOT_INITIALIZED
            return "No URL Specified"
        }
        try{
            downloadStatus=DownloadStatus.OK
            return URL(params[0]).readText()
        } catch(e:Exception){
            val errorMessage=when(e){
                is MalformedURLException->{
                    downloadStatus=DownloadStatus.NOT_INITIALIZED
                    "doInBackGround: Invalid IRL ${e.message}"
                }
                is IOException->{
                    downloadStatus=DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IO Exception reading data:${e.message}"
                }
                is SecurityException->{
                    downloadStatus=DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: SecurityException: Needs permission? ${e.message}"
                }
                else->{
                    downloadStatus=DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }
            Log.d(TAG,errorMessage)
            return  errorMessage
        }
    }
}