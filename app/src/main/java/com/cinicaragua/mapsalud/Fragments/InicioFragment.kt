package com.cinicaragua.mapsalud.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cinicaragua.mapsalud.R
import com.cinicaragua.mapsalud.databinding.FragmentCentroBinding
import com.cinicaragua.mapsalud.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        return  binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            InicioFragment()
    }
}