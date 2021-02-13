package com.amespressotest.simple.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.amespressotest.simple.R
import com.amespressotest.simple.databinding.FragmentEndBinding

/** The Activity at the end. Shows the name transmitted to it. */
class EndFragment : Fragment() {

    // arguments passés avec Navigation Component
    private val args: EndFragmentArgs by navArgs()
    private var _binding: FragmentEndBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val name = requireNotNull(args.name) { "No user name was passed to this Activity, abnormal!" }
            helloName.text = getString(R.string.end_page_name_placeholder, name)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}