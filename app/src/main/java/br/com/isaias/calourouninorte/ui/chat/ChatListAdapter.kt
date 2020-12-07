package br.com.isaias.calourouninorte.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.isaias.calourouninorte.data.model.Message
import br.com.isaias.calourouninorte.databinding.ItemReciveMessageBinding
import br.com.isaias.calourouninorte.databinding.ItemSentMessageBinding

class ChatListAdapter(private val listMessages : MutableList<Message> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SentMessageViewHolder( private val binding: ItemSentMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
        }
    }

    inner class ReciveMessageViewHolder constructor(private val binding: ItemReciveMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Message) {
            binding.message = item
        }
    }

    override fun getItemCount(): Int = listMessages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> (holder as SentMessageViewHolder).bind(listMessages[position])
            2 -> (holder as ReciveMessageViewHolder).bind(listMessages[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val sentBinding = ItemSentMessageBinding.inflate(layoutInflater, parent, false)
        val recivedBinding = ItemReciveMessageBinding.inflate(layoutInflater, parent, false)

        return when(viewType){
            0 -> SentMessageViewHolder(sentBinding)
            2 -> ReciveMessageViewHolder(recivedBinding)
            else -> SentMessageViewHolder(sentBinding)
        }
    }
    fun addMessage(message: Message){
        listMessages.add(message)
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int = if (listMessages[position].isRecived) 2 else 0
}