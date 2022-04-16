package com.example.eldenmessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentContainerView
import com.example.eldenmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var template1: EditText
    private lateinit var template2: EditText
    private lateinit var word1: EditText
    private lateinit var word2: EditText
    private lateinit var conjunction: EditText
    private lateinit var btnFinishMessage: Button
     lateinit var messagesVM: MessagesViewModel

    private lateinit var fragMain: FragmentContainerView

    fun hideMessageSelection() {
        btnFinishMessage.visibility = View.VISIBLE
        fragMain.visibility = View.GONE
    }
    private fun displayMessageSelection() {
        btnFinishMessage.visibility = View.INVISIBLE
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frag_main, MessageSelectorFragment())
            .addToBackStack(null)
            .commit()
        fragMain.visibility = View.VISIBLE
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
        btnFinishMessage = binding.btnFinishMessage
        messagesVM = MessagesViewModel()

        // Button: Toggle Message Format
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

        // EditText: Open Message Selection
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

    }

    fun confirmMessageSelection(position: Int) {
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
    fun selectWordCategory(position: Int) {
        messagesVM.selectedWordCategory.value = position
    }

}