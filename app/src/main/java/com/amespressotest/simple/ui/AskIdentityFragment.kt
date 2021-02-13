package com.amespressotest.simple.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.amespressotest.simple.R
import com.amespressotest.simple.databinding.FragmentAskIdentityBinding

/** Asks the name of the user. He cannot proceed if no name is entered. */
class AskIdentityFragment : Fragment() {

    private var _binding: FragmentAskIdentityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAskIdentityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            endButton.setOnClickListener {
                gotoNextPage()
            }

            enterNameEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    updateContinueButton()
                }

                override fun beforeTextChanged(
                    s: CharSequence?, start: Int,
                    count: Int, after: Int
                ) { /* Nothing to do. */
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) { /* Nothing to do. */
                }
            })
        }

        updateContinueButton()
    }

    /** Opens the next page, giving the typed name to it. */
    private fun gotoNextPage() {
        with(binding) {
            val name = enterNameEditText.text.trim().toString()
            findNavController().navigate(
                AskIdentityFragmentDirections.actionAskIdentityFragmentToEndFragment(
                    name
                )
            )
        }
    }

    /** Enables or disables the button according to the text. */
    private fun updateContinueButton() {
        with(binding) {
            endButton.isEnabled = enterNameEditText.text.trim()
                .isNotBlank()        // Is blank, the button is disabled.
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}