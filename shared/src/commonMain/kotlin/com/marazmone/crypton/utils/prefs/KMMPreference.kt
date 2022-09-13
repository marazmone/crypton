package com.marazmone.crypton.utils.prefs

class KMMPreference(
    private val context: KMMContext,
) {

    internal fun put(key: String, value: Int) = context.putInt(key, value)

    internal fun put(key: String, value: String) = context.putString(key, value)

    internal fun put(key: String, value: Boolean) = context.putBool(key, value)

    internal fun getInt(key: String, default: Int): Int = context.getInt(key, default)

    internal fun getString(key: String): String? = context.getString(key)

    internal fun getBool(key: String, default: Boolean = false): Boolean = context.getBool(key, default)
}