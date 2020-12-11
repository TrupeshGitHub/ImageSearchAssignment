package com.assignment.imagesearch.network

import java.io.IOException

/**
 * This is use to catch internet connection exception
 * @param message
 */
class NoInternetException(message: String) : IOException(message)