package com.jabl.practica002.data

class Generic {


    fun getUrl(parameter : Int): String
    {
        var url = ""
        when(parameter)
                {
                        1  -> url = "https://apidevseguridad.tiii.mx/api/v1" /* Desarrollo */
                        // 1  -> url = "https://apiseguridad.tiii.mx/api/v1"       /* Producción */
                        2  -> url = "/sistema/estados"



                }//Max 17
        return url
    }






}