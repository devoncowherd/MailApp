package com.example.fakemailbox.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fakemailbox.R
import com.example.fakemailbox.data.model.Mail
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

        //State Management
        var sortState = 1

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inbox, container, false)

        //Page Elements
        val settingsButton : ImageView  = view.findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            settingsButton.findNavController().navigate(R.id.action_inboxFragment_to_settingsFragment)
        }

        val sortButton : ImageView = view.findViewById(R.id.sortButton)


        //GetData
        vm.fetchMailList().observe(viewLifecycleOwner) { mailList ->

            var sortState = 0
            val recyclerView : RecyclerView = view.findViewById(R.id.mailRecycler)
            val adapter = MailAdapter(mailList)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            adapter.update()
            sortButton.setOnClickListener {
                when(sortState) {
                    0 -> {
                        ++sortState
                        mailList?.sortByDescending { it.id }
                        adapter.update()
                        Toast.makeText(requireContext(), "Sorted By Descending ID",Toast.LENGTH_SHORT).show()
                    }
                    1 ->  {
                        ++sortState
                        mailList?.sortBy { it.fromDate }
                        adapter.update()
                        Toast.makeText(requireContext(), "Sorted By Date",Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        ++sortState
                        mailList?.sortedByDescending { it.fromDate }
                        adapter.update()
                        Toast.makeText(requireContext(), "Sorted By Descending Date",Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        if(sortState >= 3){
                            sortState = 0
                        }
                        mailList?.sortBy { it.id }
                        adapter.update()
                        Toast.makeText(requireContext(), "Sorted By ID",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }


}