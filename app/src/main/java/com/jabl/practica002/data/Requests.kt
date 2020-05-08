package com.jabl.practica002.data

import android.content.Context
import org.json.JSONObject
import org.json.JSONStringer
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class Requests
{
    var url: String = ""

    private val TIMEOUT = 10*1000

    @Throws(Exception::class)
    fun httpPostRequest(method: String, json_object_send : JSONStringer) : JSONObject
    {

        var json_object = JSONObject()

        url = Generic().getUrl(1)!!
        val url_request = URL(url + method)
        val connection = url_request.openConnection() as HttpURLConnection
        connection.readTimeout   = TIMEOUT
        connection.connectTimeout = TIMEOUT
        connection.requestMethod = "POST"

        connection.instanceFollowRedirects = false
        connection.doOutput                = true
        connection.doInput                 = true
        connection.useCaches               = false
        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-Type", "application/json")

        connection.connect()
        val os = connection.getOutputStream()
        val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
        writer.write(json_object_send.toString())
        writer.flush()
        writer.close()
        os.close()

        json_object = if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedInputStream(connection.inputStream)
            val data: String = readStream(inputStream = stream)
            JSONObject(data)
        } else {
            throw Exception ("${connection.responseCode}")
        }

        return json_object
    }

    @Throws(Exception::class)
    fun httpGetRequest(req: String, method: String) : JSONObject
    {
        var json_object = JSONObject()
        val url_request = URL(url + method)
        val connection = url_request.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Content-Type", "application/json")
        json_object = if(connection.responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedInputStream(connection.inputStream)
            val data: String = readStream(inputStream = stream)
            JSONObject(data)
        } else {
            throw Exception ("${connection.responseCode}")
        }
        return json_object
    }

    fun readStream(inputStream: BufferedInputStream): String
    {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

    @Throws(Exception::class)
    fun httpPostRequestBody(urlFinal: String, jsonEnd: String?, flag: Int): String
    {
        var reqParam: String? = null

        var requestResponse = ""
        url = Generic().getUrl(1)!!
        var url = url + urlFinal
        val urlRequest = URL(url)

        with(urlRequest.openConnection() as HttpURLConnection){
            requestMethod = "POST"
            val wr = OutputStreamWriter(getOutputStream())
            if(flag == 1){
                var reqParam: String = URLEncoder.encode("", "UTF-8") + "=" + URLEncoder.encode(jsonEnd, "UTF-8")
                wr.write(reqParam)
            }
            wr.flush()

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()
                var inputLine = it.readLine()
                while (inputLine != null){
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                requestResponse = response.toString()
            }
        }
        return requestResponse
    }

}