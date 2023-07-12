package com.example.suitmedia_mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.suitmedia_mobile.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var name: String = "Anonymous"
    private lateinit var selectedUser: String
    var isSelected = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name").toString()
            if (it.containsKey("selected")) {
                selectedUser = it.getString("selected").toString()
                isSelected = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogName.text = name
        if (isSelected) {
            binding.tvSelectedUser.text = selectedUser
        }

        binding.btnChsUser.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", name)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }

        binding.btnClose.setOnClickListener {
            val intent = Intent(this@FirstFragment.requireContext(), FirstScreen::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}