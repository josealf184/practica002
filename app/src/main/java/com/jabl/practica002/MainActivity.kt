package com.jabl.practica002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jabl.practica002.business.DownloadInfo as negocio
import com.jabl.practica002.entity.EstadosCollection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var estadosCollection : EstadosCollection //declaramos la variable
        estadosCollection = EstadosCollection()// agregamos espacio en memoria a la variable

        val thread = Thread {
            estadosCollection = negocio().getEstados()
        }
        thread.start()//nos falta un hilo






    }
}
