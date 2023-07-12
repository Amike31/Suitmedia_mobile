package com.example.suitmedia_mobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmedia_mobile.adapter.ItemAdapter
import com.example.suitmedia_mobile.databinding.FragmentSecondBinding
import com.example.suitmedia_mobile.model.Item
import com.example.suitmedia_mobile.retrofit.ApiEndpoint
import com.example.suitmedia_mobile.retrofit.RetrofitClient
import kotlin.collections.ArrayList
import com.example.suitmedia_mobile.retrofit.dataClass.RawResponse
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var listItem: ArrayList<Item>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var name: String
    private lateinit var selected: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString("name").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItem = arrayListOf()
        val api = RetrofitClient.getInstance().create(ApiEndpoint::class.java)
        lifecycleScope.launch {
            val operation = GlobalScope.async(Dispatchers.Default) {
                try {
                    val res = api.getRawResponse()
                    if (res.isSuccessful) {
                        val dataItem = res.body()?.data
                        if (dataItem != null) {
                            for (i in dataItem) {
                                val id = i.id
                                val first = i.first_name
                                val last = i.last_name
                                val email = i.email
                                val avatar = i.avatar
                                listItem.add(Item(id, first, last, email, avatar))
                            }
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {Toast.makeText(context, "Connection timeout", Toast.LENGTH_SHORT).show()}
                }
            }
            operation.await()
            recyclerView = binding.rvItem
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.setHasFixedSize(true)
            adapter = ItemAdapter(listItem)
            recyclerView.adapter = adapter
        }

        binding.buttonSecond.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("selected", "lala")
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}