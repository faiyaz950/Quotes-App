package com.example.ahlulbaytquotes

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

class MainViewModel(val context: Context) : ViewModel() {
    private var quotelList : Array<Quote> = emptyArray()
    private var index = 0

    init {
        quotelList = loadQuotesFromAsset()
    }

    private fun loadQuotesFromAsset(): Array<Quote> {
        val inputStream = context.assets.open("quotess")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json , Array<Quote>::class.java)
    }

    fun getQuote() = quotelList[index]

    fun nextQuote() = quotelList[++index]

    fun previousQuote() = quotelList[--index]
}