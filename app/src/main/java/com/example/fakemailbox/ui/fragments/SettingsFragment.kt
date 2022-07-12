package com.example.fakemailbox.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.fakemailbox.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val inboxButton : ImageView = view.findViewById(R.id.inboxButton)
        inboxButton.setOnClickListener {
            inboxButton.findNavController().navigate(R.id.action_settingsFragment_to_inboxFragment)
        }

        return view
    }

}