package com.marazmone.crypton.utils.prefs

import platform.Foundation.NSUserDefaults

internal actual fun KMMContext.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

internal actual fun KMMContext.getInt(key: String, default: Int): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

internal actual fun KMMContext.putString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

internal actual fun KMMContext.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}

internal actual fun KMMContext.putBool(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

internal actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key)
}