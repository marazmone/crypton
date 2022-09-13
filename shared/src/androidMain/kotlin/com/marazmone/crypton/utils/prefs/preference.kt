package com.marazmone.crypton.utils.prefs

import com.marazmone.crypton.BuildConfig

const val PREFS_NAME = BuildConfig.LIBRARY_PACKAGE_NAME.plus("_prefs")

internal actual fun KMMContext.putInt(key: String, value: Int) {
    editor.putInt(key, value).apply()
}

internal actual fun KMMContext.getInt(key: String, default: Int): Int {
    return prefs.getInt(key, default)
}

internal actual fun KMMContext.putString(key: String, value: String) {
    editor.putString(key, value).apply()
}

internal actual fun KMMContext.getString(key: String): String? {
    return prefs.getString(key, null)
}

internal actual fun KMMContext.putBool(key: String, value: Boolean) {
    editor.putBoolean(key, value).apply()
}

internal actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return prefs.getBoolean(key, default)
}

private val KMMContext.prefs get() = getSharedPreferences(PREFS_NAME, 0)

private val KMMContext.editor get() = prefs.edit()