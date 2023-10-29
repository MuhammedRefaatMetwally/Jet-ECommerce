package com.example.domain.exceptions

import android.nfc.FormatException
import java.lang.Exception

class ParsingException(ex : Exception) : Exception(ex) {
}