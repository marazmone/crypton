package com.marazmone.crypton.utils.prefs

internal expect fun KMMContext.putInt(key: String, value: Int)

internal expect fun KMMContext.getInt(key: String, default: Int): Int

internal expect fun KMMContext.putString(key: String, value: String)

internal expect fun KMMContext.getString(key: String) : String?

internal expect fun KMMContext.putBool(key: String, value: Boolean)

internal expect fun KMMContext.getBool(key: String, default: Boolean): Boolean