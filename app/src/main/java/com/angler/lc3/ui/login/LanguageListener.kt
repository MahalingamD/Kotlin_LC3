package com.angler.lc3.ui.login

import com.angler.lc3.data.entity.LanguageMaster

interface LanguageListener {

    fun onLanguageClick(aPosition: Int, aLanguageMaster: LanguageMaster)
}