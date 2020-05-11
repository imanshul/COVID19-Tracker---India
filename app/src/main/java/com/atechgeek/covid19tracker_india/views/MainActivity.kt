package com.atechgeek.covid19tracker_india.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.atechgeek.covid19tracker_india.R
import com.atechgeek.covid19tracker_india.extensions.addFragment
import com.atechgeek.covid19tracker_india.extensions.addHideFragment
import com.atechgeek.covid19tracker_india.extensions.hideOpenFragment
import com.atechgeek.covid19tracker_india.extensions.launchMarket
import com.atechgeek.covid19tracker_india.views.home.AboutFragment
import com.atechgeek.covid19tracker_india.views.home.IndiaFragment
import com.atechgeek.covid19tracker_india.views.home.PrecautionsFragment
import com.atechgeek.covid19tracker_india.views.home.StateWiseFragment
import com.atechgeek.covid19tracker_india.views.settings.SettingsActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        setTabs()
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigation.selectedItemId = R.id.nav_india
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_india -> {
                    sendControl(indiaFragment, getString(R.string.india))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_world -> {
                    sendControl(stateWiseFragment, getString(R.string.states_ut))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_precautions -> {
                    sendControl(precautionFragment, getString(R.string.precautions))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_about -> {
                    sendControl(aboutFragmnet, getString(R.string.about))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private lateinit var lastOpen: Fragment
    private fun sendControl(navigation: Fragment, title: String) {
        toolbar.title = title
        hideOpenFragment(this, lastOpen, navigation)
        lastOpen = navigation
    }

    lateinit var indiaFragment: IndiaFragment
    lateinit var stateWiseFragment: StateWiseFragment
    lateinit var precautionFragment: PrecautionsFragment
    lateinit var aboutFragmnet: AboutFragment
    private fun setTabs() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (supportFragmentManager.fragments.size > 0) {

            for (fragment in supportFragmentManager.fragments) {

                when (fragment) {
                    is IndiaFragment -> indiaFragment = fragment
                    is StateWiseFragment -> stateWiseFragment = fragment
                    is PrecautionsFragment -> precautionFragment = fragment
                    is AboutFragment -> aboutFragmnet = fragment
                }
            }
        } else {

            indiaFragment = IndiaFragment()
            stateWiseFragment = StateWiseFragment()
            precautionFragment = PrecautionsFragment()
            aboutFragmnet = AboutFragment()

            addFragment(this, indiaFragment)

            addHideFragment(this, precautionFragment)
            addHideFragment(this, aboutFragmnet)
            addHideFragment(this, stateWiseFragment)
        }
        lastOpen = stateWiseFragment

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }

            R.id.action_rate -> {
                launchMarket(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
