package com.example.a2fragments_jan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.a2fragments_jan10.databinding.FragmentFormBinding

// Import androidx fragment
class FormFragment : Fragment() {

    // ? after type means nullable
    private var _binding: FragmentFormBinding? = null

    // !! asserting non null, don't do too often can cause NPE(null pointer exception)
    private val binding: FragmentFormBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            firstNameEt.editText?.addTextChangedListener { text ->
                nextBtn.isEnabled = text.toString().length > 8
            }

            nextBtn.setOnClickListener {
                // Create bundle to pass data in fragment transaction.
                val bundle = Bundle()
                bundle.putString("First", firstNameEt.editText?.text.toString())
                bundle.putString("Last", lastNameEt.editText?.text.toString())

                // FragmentManger allows us to perform transaction.
                // Use replace to switch between fragments.
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, InfoFragment::class.java, bundle)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    // Set binding to null to release resource for garbage collection.
    // Only in fragments.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}