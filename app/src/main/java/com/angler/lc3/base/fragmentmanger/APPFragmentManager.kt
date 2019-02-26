package com.angler.lc3.base.fragmentmanger

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.angler.lc3.R
import com.angler.lc3.base.view.BaseFragment


class APPFragmentManager
/**
 * Constructor to Initiate fragment manager
 *
 * @param aContext FragmentActivity
 */
    (private val myContext: FragmentActivity) {


    val backstackCount: Int get() {
            val aFragmentManager = myContext.supportFragmentManager
            return aFragmentManager.backStackEntryCount
        }

    /**
     * Update the Current Fragment by passing the below parameters
     *
     * @param aFragment Fragment
     * @param tag       String
     * @param aBundle   Bundle
     */
    fun updateContent(aFragment: Fragment, tag: String, aBundle: Bundle?) {
        try {

            Log.e("TAG Screen name", tag)

            // Initialise Fragment Manager
            val aFragmentManager = myContext.supportFragmentManager

            // Initialise Fragment Transaction
            val aTransaction = aFragmentManager.beginTransaction()


            if (aBundle != null) {
                aFragment.arguments = aBundle
            }

            // Add the selected fragment
            aTransaction.add(R.id.main_container, aFragment, tag)

            // add the tag to the backstack
            aTransaction.addToBackStack(tag)

            // Commit the Fragment transaction
            // aTransaction.commit ();

            aTransaction.commitAllowingStateLoss()

            myLastTag = tag

            Log.i("LastTag", myLastTag)

        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    /**
     * Clear All Fragments
     */
    fun clearAllFragments() {
        try {
            val aFragmentManager = myContext.supportFragmentManager

            for (i in 0 until aFragmentManager.backStackEntryCount) {
                aFragmentManager.popBackStack()
            }
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun oneStepBack() {
        val fts = myContext.supportFragmentManager.beginTransaction()
        val fragmentManager = myContext.supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 2) {
            fragmentManager.popBackStackImmediate()
            fts.commit()
        }
    }


    fun onBackPress() {

        try {

            val aFragmentManager = myContext.supportFragmentManager
            if (aFragmentManager.backStackEntryCount > 1) {
                aFragmentManager.popBackStack()
                aFragmentManager.executePendingTransactions()

                Log.d(
                    "TAG",
                    "CURRENT FRAGMENT BACK STACK CLASS " + aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name!!
                )


                //TODO Stop video
                val aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
                val aFragment = myContext.supportFragmentManager.findFragmentByTag(aFragmentTag)
                aFragment!!.onResume()

                if (aFragment is BaseFragment) {
                    aFragment.onResumeFragment()
                }


                aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
            }
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun onBackPress1() {

        try {
            val aFragmentManager = myContext.supportFragmentManager
            if (aFragmentManager.backStackEntryCount > 0) {
                aFragmentManager.popBackStack()
                aFragmentManager.executePendingTransactions()

                Log.d(
                    "TAG",
                    "CURRENT FRAGMENT BACK STACK CLASS " + aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name!!
                )


                //TODO Stop video
                val aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
                val aFragment = myContext.supportFragmentManager.findFragmentByTag(aFragmentTag)
                aFragment!!.onResume()

                if (aFragment is BaseFragment) {
                    aFragment.onResumeFragment()

                }

                aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
            }
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {

        /**
         * Last fragment tag
         */
        private var myLastTag = ""
    }


}
