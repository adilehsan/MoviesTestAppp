package com.ideologer.moviestestappp.`interface`

import android.os.Bundle
import androidx.fragment.app.Fragment

interface IFragmentListner {
    fun addFragment(fragment: Fragment?,isReplace: Boolean,addTostack : Boolean,tag : String)
}