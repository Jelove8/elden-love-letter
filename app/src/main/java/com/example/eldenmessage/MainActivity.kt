package com.example.eldenmessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import com.example.eldenmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val FINAL_MSG = "All message fields must be filled out."
    }

    private lateinit var fragMain: FragmentContainerView
    private lateinit var template1: EditText
    private lateinit var template2: EditText
    private lateinit var word1: EditText
    private lateinit var word2: EditText
    private lateinit var conjunction: EditText
    lateinit var messagesVM: MessagesViewModel

    private fun displayMessageSelection() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frag_main, MessageSelectionFragment())
            .addToBackStack(null)
            .commit()
        fragMain.visibility = View.VISIBLE
    }
    fun hideMessageSelection() {
        fragMain.removeAllViews()
        fragMain.visibility = View.GONE
    }

    // Called within MessageSelectorAdapter when a message component is selected.
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
        hideMessageSelection()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing lateinit variables
        template1 = binding.etTemplates1
        template2 = binding.etTemplates2
        conjunction = binding.etConjunctions
        word1 = binding.etWords1
        word2 = binding.etWords2
        fragMain = binding.fragMain
        messagesVM = MessagesViewModel()

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
         *      Open up MessageSelectionFragment
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
            if (messagesVM.isMessageComplete()) {
                binding.tvOutputMessage.text = messagesVM.getFinalMessage()
            }
            else {
                Toast.makeText(this, FINAL_MSG, Toast.LENGTH_SHORT).show()
            }
        }

    }




}