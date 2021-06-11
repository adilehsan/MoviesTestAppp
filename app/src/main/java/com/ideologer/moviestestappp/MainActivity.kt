package com.ideologer.moviestestappp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ideologer.moviestestappp.`interface`.IFragmentListner
import com.ideologer.moviestestappp.fragments.MoviesListFragment
import com.ideologer.moviestestappp.utils.Constant

class MainActivity : AppCompatActivity(), IFragmentListner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MoviesListFragment().getInstance(bundle = null), false, false, Constant.LIST_SCREEN_TAG)
    }

    override fun addFragment(
        fragment: Fragment?,
        isReplace: Boolean,
        addTostack: Boolean,
        tag: String
    ) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        if (addTostack) {
            ft.addToBackStack(tag)
        }
        if (isReplace) {
            ft.replace(R.id.content_main, fragment!!, tag)
        } else {
            ft.add(R.id.content_main, fragment!!, tag)
        }

        ft.commit()
    }

}