package com.projeto.babyblue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.projeto.babyblue.adapters.MarkerInfoAdapter


class MapaActivity : AppCompatActivity() {

    private val firestore = FirebaseFirestore.getInstance()

    private val TAG = "DOC"

    private var psicologos = arrayListOf<Psicologo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

//        psicologos.add(
//            Psicologo("Teste", "Teste", LatLng(-3.1393221,-58.4370926), "Teste", "Teste")
//        )

        getPsicologoFirebase()
    }

    private fun chamarMapa() {
        //referencia do maps
        val mapFragment = supportFragmentManager.findFragmentById(R.id.maps_fragment) as SupportMapFragment

        Log.d(TAG, psicologos.size.toString())

        psicologos.forEach { psicologo ->
            Log.d(TAG, psicologo.nomePsicologo)
        }

        mapFragment.getMapAsync { googleMap ->

            addMarkers(googleMap)

            googleMap.setInfoWindowAdapter(MarkerInfoAdapter(this))

            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()

                psicologos.forEach {
                    bounds.include(it.localizacao)
                }

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
            }

        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        psicologos.forEach { psicologo->
            val marker =  googleMap.addMarker(
                MarkerOptions()
                    .title(psicologo.nomeClinica)
                    .snippet(psicologo.endereco)
                    .position(psicologo.localizacao)
            )
            marker?.tag = psicologo
            Log.d(TAG, "marcador adicionado")
        }
    }

    private fun getPsicologoFirebase() {
        firestore.collection("psicologos")
            .get()
            .addOnSuccessListener { documents->
                for (document in documents) {
                    var geoPoint : GeoPoint? = document.getGeoPoint("localizacao")
                    if (geoPoint != null) {
                        psicologos.add(
                            Psicologo(
                                "${document.get("nomePsicologo")}",
                                "${document.get("nomeClinica")}",
                                LatLng(geoPoint.latitude, geoPoint.longitude),
                                "${document.get("endereco")}",
                                "${document.get("telefone")}"
                            )
                        )
                    }
                }

                psicologos.forEach { psicologo->
                    Log.d(TAG, psicologo.localizacao.toString())
                }

                chamarMapa()

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }

    data class Psicologo(
        val nomePsicologo: String,
        val nomeClinica: String,
        val localizacao: LatLng,
        val endereco: String,
        val telefone: String
    )

}