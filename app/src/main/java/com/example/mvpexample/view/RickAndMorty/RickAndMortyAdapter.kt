package com.example.mvpexample.view.RickAndMorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpexample.data.model.Result
import com.example.mvpexample.databinding.ItemViewCharacterBinding
import com.squareup.picasso.Picasso

class RickAndMortyAdapter(private val onItemClickListener: (String, String) -> Unit) :
    RecyclerView.Adapter<RickAndMortyAdapter.ViewHolder>() {

    private val rickAndMortyList: MutableList<Result> = mutableListOf()

    inner class ViewHolder(val binding: ItemViewCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyAdapter.ViewHolder {
        val binding =
            ItemViewCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RickAndMortyAdapter.ViewHolder, position: Int) {
        val rickAndMorty = rickAndMortyList[position]

        Picasso.get().load(rickAndMorty.image).into(holder.binding.ivCharacter)

        holder.binding.tvCharacter.text = rickAndMorty.name

        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(rickAndMorty.image, rickAndMorty.name)
        }

    }

    override fun getItemCount(): Int = rickAndMortyList.size
    fun updateList(gRickAndMortyList: MutableList<Result>) {
        rickAndMortyList.clear()
        rickAndMortyList.addAll(gRickAndMortyList)
        notifyDataSetChanged()
    }
}