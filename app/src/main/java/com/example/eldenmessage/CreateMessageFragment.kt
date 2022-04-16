package com.example.eldenmessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import com.example.eldenmessage.databinding.FragmentCreateMessageBinding


class CreateMessageFragment : Fragment() {

    companion object {
        const val FINAL_MSG = "All message fields must be filled out."
    }

    private lateinit var binding: FragmentCreateMessageBinding

    private lateinit var fragMsgComponents: FragmentContainerView
    private lateinit var template1: EditText
    private lateinit var template2: EditText
    private lateinit var word1: EditText
    private lateinit var word2: EditText
    private lateinit var conjunction: EditText

    private fun displayMessageSelection() {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frag_msgComponents, SelectMessageFragment())
            .addToBackStack(null)
            .commit()
        fragMsgComponents.visibility = View.VISIBLE
    }
    fun hideMessageSelection() {
        fragMsgComponents.removeAllViews()
        fragMsgComponents.visibility = View.GONE
    }

    // Called within MessageComponentAdapter when a message component is selected.
    fun confirmMessageComponentSelection(position: Int) {
        val messagesVM = (context as MainActivity).getMessagesVM()
        messagesVM.selectString(position)

        when (messagesVM.currentlySelecting.value!!) {
            "Template1" -> {
                template1.setText(messagesVM.msgTemplate1.value!!)
            }
            "Template2" -> {
                template2.setText(messagesVM.msgTemplate2.value!!)
            }
            "Conjunction" -> {
                conjunction.setText(messagesVM.msgConjunction.value!!)
            }
            "Word1" -> {
                word1.setText(messagesVM.msgWord1.value!!)
            }
            "Word2" -> {
                word2.setText(messagesVM.msgWord2.value!!)
            }
        }
        hideMessageSelection()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateMessageBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messagesVM: MessagesViewModel by activityViewModels()
        // Initializing lateinit variables
        fragMsgComponents = binding.fragMsgComponents
        template1 = binding.etTemplates1
        template2 = binding.etTemplates2
        conjunction = binding.etConjunctions
        word1 = binding.etWords1
        word2 = binding.etWords2

        /**
         * Toggle Message Format Button
         *  There are two possible message formats containing different message components:
         *      Format 1 -> Template + Word
         *      Format 2 -> Template1 + Word1 + Conjunction + Template2 + Word2
         *  This button will toggle the views in Main, and a Boolean variable within MessagesViewModel
         */
        binding.sbtnToggleMessageFormat.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cnstMsgConjunction.visibility = View.VISIBLE
                binding.cnstMsgPart2.visibility = View.VISIBLE
                messagesVM.msgFormat = true
            }
            else {
                binding.cnstMsgConjunction.visibility = View.GONE
                binding.cnstMsgPart2.visibility = View.GONE
                messagesVM.msgFormat = false
            }
        }

        /**
         * Message Component Buttons
         *  Each button will...
         *      Update a variable within MessagesViewModel that identifies which message component is being selected.
         *      Open up SelectMessageFragment
         */
        binding.etTemplates1.setOnClickListener {
            messagesVM.currentlySelecting.value = "Template1"
            displayMessageSelection()
        }
        binding.etTemplates2.setOnClickListener {
            messagesVM.currentlySelecting.value = "Template2"
            displayMessageSelection()
        }
        binding.etConjunctions.setOnClickListener {
            messagesVM.currentlySelecting.value = "Conjunction"
            displayMessageSelection()
        }
        binding.etWords1.setOnClickListener {
            messagesVM.currentlySelecting.value = "Word1"
            displayMessageSelection()
        }
        binding.etWords2.setOnClickListener {
            messagesVM.currentlySelecting.value = "Word2"
            displayMessageSelection()
        }

        // Button: Confirm Message Creation
        binding.btnFinishMessage.setOnClickListener {
            if ((context as MainActivity).getMessagesVM().isMessageComplete()) {
                binding.tvOutputMessage.text = (context as MainActivity).getMessagesVM().getFinalMessage()
            }
            else {
                (context as MainActivity).writeToast("All message fields must be filled out.")
            }
        }
    }
}