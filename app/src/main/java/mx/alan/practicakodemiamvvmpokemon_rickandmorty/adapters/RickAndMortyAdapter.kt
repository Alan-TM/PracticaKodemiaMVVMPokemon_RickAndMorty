package mx.alan.practicakodemiamvvmpokemon_rickandmorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.R
import mx.alan.practicakodemiamvvmpokemon_rickandmorty.data.models.rick_and_morty.Character

class RickAndMortyAdapter(val items: ArrayList<Character>) : RecyclerView.Adapter<RickAndMortyAdapter.AdapterViewHolder>() {
    class AdapterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.profile)
        val name = view.findViewById<TextView>(R.id.name)

        fun setInfo(item: Character){
            Glide.with(view).load(item.image).diskCacheStrategy(DiskCacheStrategy.NONE).into(img)

            name.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_character, parent, false))
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.setInfo(items[position])
    }

    override fun getItemCount(): Int = items.size
}