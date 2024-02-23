package com.example.mvpexample.data.model

import java.io.Serializable

data class SimpsonModel(
    var quote: String,
    var character:String,
    var image: String,
    var characterDirection: String

): Serializable
