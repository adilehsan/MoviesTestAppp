package com.ideologer.moviestestappp.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ideologer.moviestestappp.utils.Constant
import com.ideologer.moviestestappp.R
import com.ideologer.moviestestappp.`interface`.MovieItemClick
import com.ideologer.moviestestappp.databinding.ItemMoviesBinding
import com.ideologer.moviestestappp.dto.response.ResultMovies
import kotlinx.android.synthetic.main.item_movies.view.*
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

class MoviesListAdapter(
    var arrayList: ArrayList<ResultMovies>,
    var context: Context,
    var movieItemClick: MovieItemClick
) : RecyclerView.Adapter<MoviesListAdapter.MoviesHolder>(), Filterable {
    var moviesFilterList = ArrayList<ResultMovies>()
    val weakReferenceContext: WeakReference<Context?>? = WeakReference(context)

    init {
        moviesFilterList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        if (item.ifCurrentYear()) {
            holder.itemView.tvMovieYear.setTextColor(context.resources.getColor(R.color.red_color))
            holder.itemView.tvMovieYear.setTypeface(null, Typeface.BOLD);
        }
        holder.itemView.setOnClickListener {
            movieItemClick.itemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return moviesFilterList.size
    }

    inner class MoviesHolder(private val itemMoviesBinding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(itemMoviesBinding.root) {
        fun bind(moviesItem: ResultMovies) {
            itemMoviesBinding.moviesResult = moviesItem

            Glide.with(weakReferenceContext?.get()!!)
                .load(Constant.IMAGE_BASE_URL.plus(moviesItem.posterPath))
                .error(R.drawable.logo_movies)
                .placeholder(R.drawable.logo_movies)
                .into(itemMoviesBinding.ivMovies)
            itemMoviesBinding.executePendingBindings()

        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    moviesFilterList = arrayList
                } else {
                    val resultList = ArrayList<ResultMovies>()
                    for (row in arrayList) {
                        if (row.title!!.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)) || row.releaseDate!!.contains(
                                charSearch
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    moviesFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = moviesFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                moviesFilterList = results?.values as ArrayList<ResultMovies>
                notifyDataSetChanged()
            }

        }
    }

    private fun getItem(position: Int): ResultMovies = moviesFilterList.get(position)
}