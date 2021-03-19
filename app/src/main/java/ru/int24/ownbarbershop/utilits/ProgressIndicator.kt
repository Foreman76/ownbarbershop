package ru.int24.ownbarbershop.utilits

import android.view.View
import com.google.android.material.progressindicator.CircularProgressIndicator

class ProgressIndicator {

    companion object {
        fun showHideProgress(visible:Boolean, item:CircularProgressIndicator){
            when(visible){
                true -> item.visibility = View.VISIBLE
                false -> item.visibility = View.GONE
            }
        }
    }
}