package com.example.eldenmessage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eldenmessage.databinding.FragmentMessageSelectorBinding

class MessageSelectorFragment : Fragment() {



    private lateinit var binding: FragmentMessageSelectorBinding
    private lateinit var messagesVM: MessagesViewModel

    private lateinit var rcyMsgSelect: RecyclerView
    private lateinit var rcyMsgAdapter: MessageSelectionAdapter

    private fun populateRecycler() {

        when (messagesVM.currentlySelecting.value!!) {
            "Template1" -> {
                val adapter = MessageSelectionAdapter(messagesVM.getConstantTemplates(),(context as MainActivity))
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            "Template2" -> {
                val adapter = MessageSelectionAdapter(messagesVM.getConstantTemplates(),(context as MainActivity))
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            "Conjunction" -> {
                val adapter = MessageSelectionAdapter(messagesVM.getConstantConjunctions(),(context as MainActivity))
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            "Word1" -> {
                val adapter = MessageSelectionAdapter(messagesVM.getWordsByCategory(0),(context as MainActivity))
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            "Word2" -> {
                val adapter = MessageSelectionAdapter(messagesVM.getWordsByCategory(0),(context as MainActivity))
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessageSelectorBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing lateinit variables
        messagesVM = (context as MainActivity).messagesVM
        rcyMsgSelect = binding.rcyMsgSelect

        // Button: Goes back to main activity display
        binding.btnCancelMsgSelect.setOnClickListener {
            (context as MainActivity).hideMessageSelection()
        }

        // Populating recyclerview with correct data
        when (messagesVM.currentlySelecting.value) {
            "Template1" -> {
                binding.tvHeaderMsgSelect.text = "Choose a template"
                populateRecycler()
            }
            "Template2" -> {
                binding.tvHeaderMsgSelect.text = "Choose a template"
                populateRecycler()
            }
            "Conjunction" -> {
                binding.tvHeaderMsgSelect.text = "Choose a conjunction"
                populateRecycler()
            }
            "Word1" -> {
                binding.tvHeaderMsgSelect.text = "Choose a word"
                binding.llWordCategories.visibility = View.VISIBLE
                populateRecycler()
            }
            "Word2" -> {
                binding.tvHeaderMsgSelect.text = "Choose a word"
                binding.llWordCategories.visibility = View.VISIBLE
                populateRecycler()
            }
        }

        // Buttons to change Word Category Selection
        for ((c,child) in binding.llWordCategories.children.withIndex()) {
            child as Button
            child.setOnClickListener {
                messagesVM.selectedWordCategory.value = c
                rcyMsgAdapter.updateAdapterData(messagesVM.getWordsByCategory(messagesVM.selectedWordCategory.value!!))
            }
        }
    }
}