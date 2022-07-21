package com.projeto.babyblue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val psicologo = marker.tag as? MapaActivity.Psicologo ?: return null

       val view =  LayoutInflater.from(context).inflate(R.layout.custom_marker_info, null)

        view.findViewById<TextView>(R.id.txt_title).text  = psicologo.nomeClinica
        view.findViewById<TextView>(R.id.txt_addres).text = psicologo.endereco
        view.findViewById<TextView>(R.id.txt_phone).text = psicologo.telefone

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }
}