package com.cinicaragua.mapsalud.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cinicaragua.mapsalud.Entities.Centro
import com.cinicaragua.mapsalud.databinding.ItemCentroBinding
import com.google.android.material.snackbar.Snackbar

class CentrosAdapter (private var centros : ArrayList<Centro>) : RecyclerView.Adapter<CentrosAdapter.ViewHolderCentro>(){

    inner  class ViewHolderCentro(val binding : ItemCentroBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(centro : Centro){
            binding.apply {
                txtId.text = centro.nombreCentrosalud
                txtUsername.text = centro.Direccion
                txtPassword.text = centro.municipio

                botonAlerta.setOnClickListener{
                    Snackbar.make(binding.root, centro.nombreCentrosalud, 6000).show()
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCentro {
        val binding = ItemCentroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCentro(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderCentro, position: Int) {
        holder.bind(centros[position])
    }

    override fun getItemCount(): Int {
        return centros.size
    }

    fun updateCentros(nuevosCentros : ArrayList<Centro>){
        centros = nuevosCentros
        this.notifyDataSetChanged()
    }
}