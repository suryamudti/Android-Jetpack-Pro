package com.surya.androidjetpackpro.utils

import java.io.IOException

/**
 * Created by suryamudti on 08/09/2019.
 */
class ApiException(message:  String) : IOException(message)

class NoInternetException(message: String) : IOException(message)