package com.atechgeek.covid19tracker_india.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.atechgeek.covid19tracker_india.R

/**
 * Created by Anshul Thakur on 21/3/20.
 */

fun openFragment(
    act: FragmentActivity?,
    fragment: Fragment,
    shouldAnimate: Boolean,
    shouldAdd: Boolean
) {
    act?.apply {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        if (shouldAnimate) {
            // ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
        }

        ft.replace(R.id.container, fragment, fragment::class.java.canonicalName)

        if (shouldAdd)
            ft.addToBackStack(fragment::class.java.canonicalName)

        ft.commit()
    }

}


fun addFragment(act: FragmentActivity?, fragment: Fragment) {
    act?.apply {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.add(R.id.container, fragment, fragment::class.java.canonicalName)
        ft.commit()
    }
}

fun addHideFragment(act: FragmentActivity?, fragmentHide: Fragment) {
    act?.apply {
        supportFragmentManager.beginTransaction().add(R.id.container, fragmentHide)
            .hide(fragmentHide).commit()
    }
}

fun hideOpenFragment(act: FragmentActivity?, fragmentHide: Fragment, fragmentOpen: Fragment) {
    act?.apply {
        if (fragmentHide == fragmentOpen)
            supportFragmentManager.beginTransaction().show(fragmentOpen).commit()
        else
            supportFragmentManager.beginTransaction().hide(fragmentHide).show(fragmentOpen).commit()
    }
}