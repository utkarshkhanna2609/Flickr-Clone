package com.example.flickrbrowse

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class RecyclerItemClickListener (context: Context,recyclerview: RecyclerView,private val listener:OnRecyclerClickListener)
    :RecyclerView.SimpleOnItemTouchListener()
{
    private val TAG="RecyclerClicklistener"

    interface OnRecyclerClickListener{
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }
    private val gestureDetector=GestureDetectorCompat(context,object:GestureDetector.SimpleOnGestureListener(){
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(TAG,".onSingleTapUp:starts")
            val childView=recyclerview.findChildViewUnder(e.x,e.y)
            Log.d(TAG,".onLongPress calls listener .onItemClick")
            if (childView != null) {
                listener.onItemClick(childView,recyclerview.getChildAdapterPosition(childView))
                Log.d(TAG,"null statement satisfied")
            }
            else Log.d(TAG,"null statement not satisfied")
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            Log.d(TAG,".onLongPress:starts")
            val childView=recyclerview.findChildViewUnder(e.x,e.y)
            Log.d(TAG,".onLongPress calls listener .onItemLongClick")
            if (childView != null) {
                listener.onItemLongClick(childView,recyclerview.getChildAdapterPosition(childView))
                Log.d(TAG,"null statement satisfied")
            }
            else Log.d(TAG,"null statement not satisfied")

        }
    })
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        Log.d(TAG, "onInterceptTouchEvent starts $e")
        val result=gestureDetector.onTouchEvent(e)
        Log.d(TAG, "onIntercept() returns $result")
        //return super.onInterceptTouchEvent(rv, e)
        return result
    }
}