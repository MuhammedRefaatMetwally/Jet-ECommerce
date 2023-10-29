package com.example.domain.exceptions

class ServerError(val status: String, val serverMessage: String, val statsCode: Int) :
    Exception(serverMessage)