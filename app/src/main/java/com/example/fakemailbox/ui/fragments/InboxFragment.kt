package com.example.fakemailbox.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakemailbox.R
import com.example.fakemailbox.shared.MailViewModel
import com.example.fakemailbox.ui.adapters.MailAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InboxFragment : Fragment() {

    val vm : MailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inbox, container, false)

        //Page Elements
        val settingsButton : ImageView  = view.findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            settingsButton.findNavController().navigate(R.id.action_inboxFragment_to_settingsFragment)
        }

        //GetData
        vm.fetchMailList().observe(viewLifecycleOwner) {
            val recyclerView : RecyclerView = view.findViewById(R.id.mailRecycler)
            val adapter = MailAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            adapter.update()
        }

        return view
    }

}