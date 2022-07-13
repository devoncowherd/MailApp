package com.example.fakemailbox.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.fakemailbox.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.jvm.internal.impl.util.Check

@AndroidEntryPoint
class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        //Page Elements
        val inboxButton : ImageView = view.findViewById(R.id.inboxButton)
        val deleteSpamBox : CheckBox = view.findViewById(R.id.checkSpamDeletion)
        val cacheBox : CheckBox = view.findViewById(R.id.checkCacheClear)
        val syncBox : CheckBox = view.findViewById(R.id.checkAutoSync)

        //Restores preferences
        getPrefs(deleteSpamBox,cacheBox,syncBox)


        //Update Preferences
        deleteSpamBox.setOnClickListener {
            storePrefs(deleteSpamBox, cacheBox, syncBox)
        }


        cacheBox.setOnClickListener {
            storePrefs(deleteSpamBox, cacheBox, syncBox)
        }

        syncBox.setOnClickListener {
            storePrefs(deleteSpamBox, cacheBox, syncBox)
        }

        //Navigate
        inboxButton.setOnClickListener {
            inboxButton.findNavController().navigate(R.id.action_settingsFragment_to_inboxFragment)
        }



        return view
    }

    //Storing User Preferences
    private fun storePrefs(deleteSpamBox : CheckBox, cacheBox : CheckBox, syncBox : CheckBox) {
        val sharedPrefs = this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        sharedPrefs?.edit()?.apply {
            putBoolean("SPAM_KEY", deleteSpamBox.isChecked)
            putBoolean("CACHE_KEY", cacheBox.isChecked)
            putBoolean("SYNC_KEY", syncBox.isChecked)
        }?.apply()

        Toast.makeText(requireContext(), "Preferences Updated!", Toast.LENGTH_SHORT).show()
    }

    private fun getPrefs(deleteSpamBox : CheckBox, cacheBox : CheckBox, syncBox : CheckBox) {
        val sharedPrefs = this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val settingSpam = sharedPrefs?.getBoolean("SPAM_KEY", false)
        val settingCache = sharedPrefs?.getBoolean("CACHE_KEY", false)
        val settingSync = sharedPrefs?.getBoolean("SYNC_KEY", false)

        deleteSpamBox.isChecked = settingSpam!!
        cacheBox.isChecked = settingCache!!
        syncBox.isChecked = settingSync!!
    }
}