package com.apex.codeassesment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apex.codeassesment.R
import com.apex.codeassesment.databinding.ItemUserBinding
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.util.ex.load

class UserListAdapter(
    private var list: List<User>,
    private val onItemClick: (User) -> Unit
): RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<User>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MyViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)  {

        fun bind(item: User){
            binding.image.load(item.picture.thumbnail)
            binding.name.text = binding.root.context.getString(R.string.details_name,item.name.first,item.name.last)
            binding.mainEmail.text = item.email
            binding.detailsButton.setOnClickListener {
                onItemClick.invoke(list[adapterPosition])
            }
        }
    }
}