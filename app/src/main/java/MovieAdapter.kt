//package com.example.hotel
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.hotel.data.Movie
//import com.example.hotel.databinding.ItemMovieBinding
//
////the class takes a list of movie objects as a constructor parameter
////it inherits from the recyclerview.adapter
//class MovieAdapter(private var movies: List<Movie>) :
//    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
//
//    private var originalMovies: List<Movie> = movies
////this class movieviewholder inherits from the recyclerview.viewholder
//    //it holds references to the views in the movie_item.xml layout
//    class MovieViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {
//    val titleTextView: TextView = itemView.findViewById(R.id.titleTextMovie)
//    val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
//     val descriptionTextView:TextView=itemView.findViewById(R.id.descriptionTextView)
//}
//    //this function is called when the recyclerview needs a new viewholder
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
//        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
//        return MovieViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val movie = movies[position]
//        holder.titleTextView.text = movie.title
//        holder.descriptionTextView.text = movie.overview
//
//        // Check if posterPath is null
//        if (!movie.posterPath.isNullOrEmpty()) {
//            Glide.with(holder.itemView.context)
//                .load(movie.posterPath)
//                .into(holder.posterImageView)
//        } else {
//            // Load a placeholder image
//            Glide.with(holder.itemView.context)
//                .load(R.drawable.meal) // Replace with your placeholder
//                .into(holder.posterImageView)
//        }
//    }
//
//    override fun getItemCount(): Int = movies.size
//
//    fun updateMovies(newMovies: List<Movie>) {
//        movies = newMovies
//        originalMovies = newMovies
//        notifyDataSetChanged()
//    }
//
//    fun filterMovies(query: String?) {
//        if (query.isNullOrEmpty()) {
//            movies = originalMovies
//        } else {
//            val filteredList = originalMovies.filter { movie ->
//                movie.title?.contains(query, ignoreCase = true) == true
//            }
//            movies = filteredList
//        }
//        notifyDataSetChanged()
//    }
//}