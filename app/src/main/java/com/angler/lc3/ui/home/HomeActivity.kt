package com.angler.lc3.ui.home

import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.angler.lc3.R
import com.angler.lc3.base.fragmentmanger.APPFragmentManager
import com.angler.lc3.base.view.BaseActivity
import com.angler.lc3.ui.main.MainFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeContract.View, NavigationView.OnNavigationItemSelectedListener {


    lateinit var mHomePresenter: HomePresenter
    lateinit var mFragmentManager: APPFragmentManager
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    var navItemIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        myContext = this@HomeActivity

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        mHomePresenter = HomePresenter(this, myContext)
        mHomePresenter.initPresenter()

        setUp()
    }

    override fun onFragmentAttached() {
        mHomePresenter.attachView(this)
    }

    private fun setUp() {
        setSupportActionBar(toolbar)

        toolbar.navigationIcon = null

        val toggle = ActionBarDrawerToggle(
            this, drawer_view, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawer_view.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(p0: Int) {}

            override fun onDrawerSlide(p0: View, p1: Float) {}

            override fun onDrawerClosed(p0: View) {}

            override fun onDrawerOpened(p0: View) {
                hideKeyboard()
            }
        })

        drawer_view.addDrawerListener(toggle)
        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = false
        navigation_view.setNavigationItemSelectedListener(this)
    }


    override fun initActivity() {
        mFragmentManager = APPFragmentManager(myContext)
        activity_menu_right.setOnClickListener(View.OnClickListener {
            if (drawer_view.isDrawerOpen(Gravity.END)) {
                drawer_view.openDrawer(Gravity.END)
            } else {
                drawer_view.openDrawer(Gravity.END)
            }
        })

        callDefaultFragment()
    }

    override fun callDefaultFragment() {
        mFragmentManager.updateContent(MainFragment(), "", null)
    }


    override fun onNavigationItemSelected(aMenu: MenuItem): Boolean {

        drawer_view.closeDrawer(GravityCompat.END)
        when (aMenu.itemId) {
            R.id.nav_item_home -> {
                val aMainFragment = MainFragment()
                loadHomeFragment(aMainFragment)
                return true
            }
            R.id.nav_item_language -> {
                showLanguageAlertDialog()
                return true
            }
            R.id.nav_item_about -> {
                val aMainFragment = MainFragment()
                loadHomeFragment(aMainFragment)
                return true
            }
            else -> {
                val aMainFragment = MainFragment()
                loadHomeFragment(aMainFragment)
                return true
            }
        }

    }

    private fun loadHomeFragment(aFragment: Fragment) {
        mFragmentManager.clearAllFragments()
        mFragmentManager.updateContent(aFragment, "", null)
    }


    private fun showLanguageAlertDialog() {
    }


    override fun onFragmentDetached(tag: String) {}
}
