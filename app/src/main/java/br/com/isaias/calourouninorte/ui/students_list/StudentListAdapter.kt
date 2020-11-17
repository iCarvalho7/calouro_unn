package br.com.isaias.calourouninorte.ui.students_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.isaias.calourouninorte.data.model.User
import br.com.isaias.calourouninorte.databinding.ItemStudentsBinding


class StudentListAdapter(private val viewModel: StudentListViewModel, val listener: OnItemClickListener) :
    ListAdapter<User, StudentListAdapter.ViewHolder>(UserDiffCallback()) {

    interface OnItemClickListener{
        fun onclick(item: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStudentsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }

    inner class ViewHolder(private val binding: ItemStudentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: StudentListViewModel, item: User) {

            binding.root.setOnClickListener {
                listener.onclick(item)
            }
            binding.viewModel = viewModel
            binding.user = item
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.UIID == newItem.UIID
        override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
    }
}
