package com.jabl.practica002.data

import android.content.Context
import com.jabl.practica002.entity.EstadosCollection
import com.jabl.practica002.entity.Estados
import org.json.JSONObject


class DownloadInfo
{
    fun getEstados(url: String) : EstadosCollection
    {
        var eCollection = EstadosCollection()
        var response : String = ""

        response = Requests().httpPostRequestBody(url,null,0)

        var json = JSONObject(response)
        val msg : String = json.get("message").toString()

        if(msg == "ok"){
            val totalArray  = json.getJSONArray("data")

            if(totalArray.length() > 0){

                for(i in 0..(totalArray.length()-1) ){
                    var estados = Estados()
                    var item = totalArray.getJSONObject(i)
                    estados.Id = item.getInt("idEstado")
                    estados.Nombre = item.getString("nombreEstado")
                    estados.Abreviatura = item.getString("abreviatura")
                    estados.Clave = item.getString("claveSAT")

                   eCollection.add(estados)
                }
            }
        }
     return eCollection
    }





}