package com.example.eldenmessage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import com.example.eldenmessage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val FINAL_MSG = "All message fields must be filled out."
    }

    private lateinit var messagesVM: MessagesViewModel
    private lateinit var fragMain: FragmentContainerView
    private lateinit var fragSub: FragmentContainerView
    private lateinit var createMessageFragment: CreateMessageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing lateinit variables
        val messagesViewModel: MessagesViewModel by viewModels()
        messagesVM = messagesViewModel
        fragMain = binding.fragMain
        fragSub = binding.fragSub

        // Navbar Buttons
        binding.btnNav1.setOnClickListener {
            displayMessageCreation()
        }


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



    fun displayMessageCreation() {
        fragSub.visibility = View.GONE
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frag_main, createMessageFragment)
            .addToBackStack(null)
            .commit()
    }
    fun displayMessageSelection() {
        fragSub.visibility = View.VISIBLE
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.frag_sub, SelectMessageFragment())
            .addToBackStack(null)
            .commit()
    }
    fun setCreateMessageFrag(fragment: CreateMessageFragment) {
        createMessageFragment = fragment
    }
    fun getCreateMessageFrag(): CreateMessageFragment {
        return createMessageFragment
    }
    fun hideSubFragment() {
        fragSub.removeAllViews()
        fragSub.visibility = View.GONE
    }

}