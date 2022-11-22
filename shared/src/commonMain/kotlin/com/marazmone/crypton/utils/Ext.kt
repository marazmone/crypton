package com.marazmone.crypton.utils

import io.realm.kotlin.MutableRealm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.RealmObject

fun <T : RealmObject> MutableRealm.insertOrUpdate(instance: T) =
    copyToRealm(instance, UpdatePolicy.ALL)

val Boolean?.orFalse: Boolean get() = this ?: false