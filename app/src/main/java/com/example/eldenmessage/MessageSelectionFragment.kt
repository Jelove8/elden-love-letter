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
import com.example.eldenmessage.databinding.FragmentMessageSelectionBinding

class MessageSelectionFragment : Fragment() {

    private lateinit var binding: FragmentMessageSelectionBinding

    private lateinit var rcyMsgSelect: RecyclerView
    private lateinit var rcyMsgAdapter: MessageSelectionAdapter

    /**
     * Populating Recycler View
     *  Depending on which message component is currently being selected,
     *  the appropriate data from MessagesViewModel is passed through MessageSelectionAdapter.
     */
    private fun populateRecycler() {
        val messagesVM = (context as MainActivity).getMessagesVM()
        val currentlySelecting = messagesVM.currentlySelecting.value!!
        when {
            currentlySelecting.contains("Template") -> {
                val adapter = MessageSelectionAdapter(messagesVM.getConstantTemplates(),parentFragment as CreateMessageFragment)
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            currentlySelecting.contains("Conjunction") -> {
                val adapter = MessageSelectionAdapter(messagesVM.getConstantConjunctions(),parentFragment as CreateMessageFragment)
                rcyMsgSelect.layoutManager = LinearLayoutManager(activity)
                rcyMsgSelect.adapter = adapter
                rcyMsgAdapter = adapter
            }
            currentlySelecting.contains("Word") -> {
                val adapter = MessageSelectionAdapter(messagesVM.getWordsByCategory(0),parentFragment as CreateMessageFragment)
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
        binding = FragmentMessageSelectionBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initializing lateinit variables
        val messagesVM = (context as MainActivity).getMessagesVM()
        rcyMsgSelect = binding.rcyMsgSelect

        // Button to clear and hide this Fragment's container
        binding.btnCancelMsgSelect.setOnClickListener {
            (parentFragment as CreateMessageFragment).hideMessageSelection()
        }

        // Initializing Fragment UI
        when (messagesVM.currentlySelecting.value) {
            "Template1" -> {
                binding.tvHeaderMsgSelect.text = "Choose a template"
            }
            "Template2" -> {
                binding.tvHeaderMsgSelect.text = "Choose a template"
            }
            "Conjunction" -> {
                binding.tvHeaderMsgSelect.text = "Choose a conjunction"
            }
            "Word1" -> {
                binding.tvHeaderMsgSelect.text = "Choose a word"
                binding.llWordCategories.visibility = View.VISIBLE
            }
            "Word2" -> {
                binding.tvHeaderMsgSelect.text = "Choose a word"
                binding.llWordCategories.visibility = View.VISIBLE
            }
        }
        populateRecycler()

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