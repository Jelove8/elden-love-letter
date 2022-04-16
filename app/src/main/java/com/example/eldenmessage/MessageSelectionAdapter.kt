package com.example.eldenmessage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageSelectionAdapter(
    private var adapterData: MutableList<String>,
    private val mainActivity: MainActivity
    ) : RecyclerView.Adapter<MessageSelectionAdapter.MessageSelectionViewHolder>() {

    /**
     * Update Word Categories Button
     *  This function is called in MessageSelectionFragment
     *  and serves to invoke notifyDataSetChanged() on this adapter
     *  to update the words listed within the recycler view.
     */
    fun updateAdapterData(newData: MutableList<String>) {
        adapterData = newData
        this.notifyDataSetChanged()
    }

    class MessageSelectionViewHolder(ItemView: View, thisAdapter: MessageSelectionAdapter) : RecyclerView.ViewHolder(ItemView){
        val tvCurrentSelection: TextView = itemView.findViewById(R.id.tv_messageSelection)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_message_template, parent, false)

        return MessageSelectionViewHolder(view,this)
    }

    override fun onBindViewHolder(
        holder: MessageSelectionViewHolder,
        position: Int
    ) {
        holder.tvCurrentSelection.text = adapterData[position]
        holder.tvCurrentSelection.setOnClickListener {
            mainActivity.confirmMessageComponentSelection(position)
        }
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

}