package com.example.a2fragments_jan10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.a2fragments_jan10.databinding.FragmentPasswordFormBinding

// Import androidx fragment
class PasswordFragment: Fragment() {

    // Let the binding be nullable
    private var _binding: FragmentPasswordFormBinding? = null

    // !! Assert the binding is non-null for certain, but be careful about an NPE (null pointer exception)
    private val binding: FragmentPasswordFormBinding get() = _binding!!

    private var firstName = ""
    private var lastName = ""
    private var email = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            passwordEt.editText?.addTextChangedListener { text ->
                Log.d(EmailFragment.TAG, text.toString())
                firstName = (arguments?.get("First") ?: "First").toString()
                lastName = (arguments?.get("Last") ?: "Last").toString()
                email = (arguments?.get("Email") ?: "Email").toString()
                nextBtn.isEnabled = text.toString().length > 4
            }

            nextBtn.setOnClickListener {
                Log.d(EmailFragment.TAG, "nexBtn Clicked")
                // Create bundle to pass data in fragment transaction.
                val password = passwordEt.editText?.text.toString()
                val bundle = Bundle()
                bundle.putString("First", firstName)
                bundle.putString("Last", lastName)
                bundle.putString("Email", email)
                bundle.putString("Password", password)
                Log.d(EmailFragment.TAG, "email is $email")

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

    companion object {
        val TAG = PasswordFragment::class.java.name
    }
}
