package com.amespressotestfragments.simple.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amespressotestfragments.simple.databinding.FragmentStartBinding

/** The first shown Activity. */
class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            continueButton.setOnClickListener {
                gotoNextPage()
            }
        }

    }

    /** Opens the next page */
    private fun gotoNextPage() {
        findNavController().navigate(
            StartFragmentDirections.actionStartFragmentToAskIdentityFragment()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}