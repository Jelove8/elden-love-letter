package com.example.eldenmessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import com.example.eldenmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val FINAL_MSG = "All message fields must be filled out."
    }

    private lateinit var messagesVM: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val messagesViewModel: MessagesViewModel by viewModels()
        messagesVM = messagesViewModel

    }

    fun writeToast(msg: String, durationShort: Boolean = true) {
        if (durationShort) {
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        }
    }
    fun getMessagesVM() : MessagesViewModel {
        return messagesVM
    }

}