package com.rafaelduransaez.mycinema.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelduransaez.domain.entities.Movie
import com.rafaelduransaez.mycinema.R
import com.rafaelduransaez.mycinema.databinding.MovieListItemBinding
import com.rafaelduransaez.mycinema.extensions.basicDiffUtil
import com.rafaelduransaez.mycinema.extensions.inflate

class MoviesAdapter(private val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(
        basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.movie_list_item)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieListItemBinding.bind(view)
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }
}
