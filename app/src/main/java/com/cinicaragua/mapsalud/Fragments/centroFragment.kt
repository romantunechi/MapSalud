package com.cinicaragua.mapsalud.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.preference.PreferenceManager
import com.cinicaragua.mapsalud.AppDatabases.AppDatabase
import com.cinicaragua.mapsalud.R
import com.cinicaragua.mapsalud.Repositories.MainRepository
import com.cinicaragua.mapsalud.ViewModels.MainViewModel
import com.cinicaragua.mapsalud.ViewModels.ViewModelFactory
import com.cinicaragua.mapsalud.databinding.FragmentCentroBinding
import com.google.android.material.snackbar.Snackbar
import org.osmdroid.config.Configuration.getInstance
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker


class centroFragment : DialogFragment() {

    private  var _binding : FragmentCentroBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : MainViewModel by activityViewModels {
            ViewModelFactory(MainRepository(AppDatabase.getDatabase(requireContext())))
        }

        val map = binding.mapaSalud

        getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()))

        map.setTileSource(TileSourceFactory.MAPNIK)

        map.controller.setZoom(15.0)
        map.controller.setCenter(GeoPoint(12.13282, -86.2504))

        //Ejemplo de como crear los marcadores que representen los centros de salud
        //En teoria, ustedes aqui obtendrian la lista de lugares que estan ingresados en la base de datos y crearian un Marker por cada uno

        viewModel.selectAllCentros().observe(viewLifecycleOwner, { centros ->

            map.overlays.clear()

            centros.forEach { centro ->

                val marker = Marker(map)
                marker.position = GeoPoint(centro.latitud, centro.longitud)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.setOnMarkerClickListener { _, _ ->

                    // Crear y mostrar un AlertDialog con la información del centro
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Info")
                    builder.setMessage(
                        "Nombre: ${centro.nombreCentrosalud}\n" +
                                "Teléfono: ${centro.numeroTelefonocentro}\n" +
                                "Distrito: ${centro.distrito}\n" +
                                "Zona: ${centro.Zona}\n" +
                                "Dirección: ${centro.Direccion}\n" +
                                "Municipio: ${centro.municipio}\n" +
                                "Latitud: ${centro.latitud}\n" +
                                "Longitud: ${centro.longitud}\n"
                    )
                    builder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss() // Cierra el diálogo al hacer clic en OK
                    }
                    builder.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss() // Cierra el diálogo al hacer clic en Cancelar
                    }
                    builder.create().show()

                    true // Indica que el clic en el marcador ha sido manejado
                }
                map.overlays.add(marker)

            }

        })

        binding.botonSalir.setOnClickListener {

            this.dismiss()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCentroBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    companion object {

        @JvmStatic
fun newInstance() = centroFragment()
    }
}