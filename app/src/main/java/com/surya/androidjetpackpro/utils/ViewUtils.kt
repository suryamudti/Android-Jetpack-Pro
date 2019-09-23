package com.surya.androidjetpackpro.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by suryamudti on 06/08/2019.
 */

fun Context.toast(message: String){
    Toast.makeText(this,message, Toast.LENGTH_LONG).show()
}