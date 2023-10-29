package com.example.domain.exceptions

import java.io.IOException
import java.lang.Exception

class ServerTimeOutException(ex : Exception) : IOException(ex) {
}