package com.jabl.practica002.business

import com.jabl.practica002.entity.EstadosCollection
import com.jabl.practica002.data.DownloadInfo as data

class DownloadInfo
{
    fun getEstados() : EstadosCollection
    {
       var estadosCollection : EstadosCollection // declaramos la variable
        estadosCollection = EstadosCollection() // agregamos espacio en memoria a la variable


      estadosCollection = data().getEstados("/sistema/estados")//llenamos estados collection con la informacion ya tratada por el data

        return estadosCollection

    }
}