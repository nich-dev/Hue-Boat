package com.nicholasholley.dev.hueboatsdk.util

import android.util.Log

fun String?.d() {
    Log.d("HueBoatSdk", this)
}

fun String?.e() {
    Log.e("HueBoatSdk", this)
}

fun String?.w() {
    Log.w("HueBoatSdk", this)
}