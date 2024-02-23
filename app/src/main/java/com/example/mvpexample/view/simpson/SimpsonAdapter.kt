package com.example.mvpexample.view.simpson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpexample.data.model.SimpsonModel
import com.example.mvpexample.databinding.ItemViewCharacterBinding
import com.squareup.picasso.Picasso

class SimpsonAdapter(
    private val onItemClickListener: (String, String) -> Unit
) : RecyclerView.Adapter<SimpsonAdapter.SimpsonViewHolder>() {

    private val simpsonList: MutableList<SimpsonModel> = mutableListOf()

    inner class SimpsonViewHolder(val binding: ItemViewCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpsonViewHolder {
        val binding =
            ItemViewCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimpsonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return simpsonList.size
    }

    override fun onBindViewHolder(holder: SimpsonViewHolder, position: Int) {
        val simpson = simpsonList[position]

        Picasso.get().load(simpson.image).into(holder.binding.ivCharacter)

        holder.binding.tvCharacter.text = simpson.character

        holder.binding.root.setOnClickListener {
            onItemClickListener.invoke(simpson.character, simpson.image)
        }
    }

    fun setData(simpsonList: MutableList<SimpsonModel>) {
        this.simpsonList.clear()
        this.simpsonList.addAll(simpsonList)
        notifyDataSetChanged()
    }
}