package com.example.vinyls

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnColeccionista = requireView().findViewById<Button>(R.id.btnIrColeccionista)
        val btnUsuario = requireView().findViewById<Button>(R.id.btnIrUsuario)

        btnColeccionista.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pantallaUnoFragment)
        }
        btnUsuario.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pantallaUnoFragment)
        }

    }

}