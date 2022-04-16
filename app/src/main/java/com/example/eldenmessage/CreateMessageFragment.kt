package com.example.eldenmessage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.eldenmessage.databinding.FragmentCreateMessageBinding

class CreateMessageFragment : Fragment() {

    private lateinit var binding: FragmentCreateMessageBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var messagesVM: MessagesViewModel

    private lateinit var template1: EditText
    private lateinit var template2: EditText
    private lateinit var word1: EditText
    private lateinit var word2: EditText
    private lateinit var conjunction: EditText


    // Called within MessageComponentAdapter when a message component is selected.
    fun confirmMessageComponentSelection(position: Int) {
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

        mainActivity = context as MainActivity
        mainActivity.setCreateMessageFrag(this)
        messagesVM = mainActivity.getMessagesVM()

        // Initializing lateinit variables
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
            mainActivity.displayMessageSelection()
        }
        binding.etTemplates2.setOnClickListener {
            messagesVM.currentlySelecting.value = "Template2"
            mainActivity.displayMessageSelection()
        }
        binding.etConjunctions.setOnClickListener {
            messagesVM.currentlySelecting.value = "Conjunction"
            mainActivity.displayMessageSelection()
        }
        binding.etWords1.setOnClickListener {
            messagesVM.currentlySelecting.value = "Word1"
            mainActivity.displayMessageSelection()
        }
        binding.etWords2.setOnClickListener {
            messagesVM.currentlySelecting.value = "Word2"
            mainActivity.displayMessageSelection()
        }

        // Button: Confirm Message Creation
        binding.btnFinishMessage.setOnClickListener {
            if (mainActivity.getMessagesVM().isMessageComplete()) {
                binding.tvOutputMessage.text = messagesVM.getFinalMessage()
            }
            else {
                mainActivity.writeToast("All message fields must be filled out.")
            }
        }
    }
}