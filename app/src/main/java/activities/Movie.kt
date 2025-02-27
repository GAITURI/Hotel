package activities

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.example.hotel.MovieAdapter
import com.example.hotel.MovieRetrofitInstance
import com.example.hotel.MovieViewModelFactory
import com.example.hotel.R
import com.example.hotel.viewModels.MovieViewModel
import com.example.hotel.databinding.ActivityMovieBinding
import com.example.hotel.repositories.MovieRepository

class Movie : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActivityMovieBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MovieViewModel
    private lateinit var searchView:FloatingSearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_movie)
        recyclerView= findViewById(R.id.movieRecyclerView)
        searchView= findViewById(R.id.floating_search_view)
        progressBar= findViewById(R.id.progressBar)
        errorTextView= findViewById(R.id.errorTextView)

        recyclerView.layoutManager=LinearLayoutManager(this)
        movieAdapter= MovieAdapter(emptyList())
        recyclerView.adapter= movieAdapter
        val apiInterface= MovieRetrofitInstance.api
        val repository=MovieRepository(apiInterface)
        val viewModelFactory= MovieViewModelFactory(repository)

        viewModel= ViewModelProvider(this,viewModelFactory)[MovieViewModel::class.java]

        viewModel.movies.observe(this){
            movies->movieAdapter.updateMovies(movies)

        }

        viewModel.isLoading.observe(this){
            isLoading->progressBar.visibility=if (isLoading) ProgressBar.VISIBLE else ProgressBar.GONE

        }
        setUpFloatingSearchView()

    }

    private fun setUpFloatingSearchView() {
        searchView.setOnQueryChangeListener(object : FloatingSearchView.OnQueryChangeListener {
            override fun onSearchTextChanged(oldQuery: String?, newQuery: String?) {
                Log.d(
                    "MovieActivity",
                    "onSearchTextChanged:oldQuery=$oldQuery,newQuery=$newQuery"
                )
                if (newQuery.isNullOrEmpty()) {
                    viewModel.searchMovies("")
                } else if (newQuery.length >= 3) {
                    viewModel.searchMovies(newQuery)
                }
            }
        })

        searchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener  {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                Log.d("MovieActivity", "onSuggestionClicked: searchSuggestion=$searchSuggestion")
            }

            override fun onSearchAction(currentQuery: String?) {
                Log.d("MovieActivity", "onSearchAction:query=$currentQuery")
                viewModel.searchMovies(currentQuery ?: "")
                searchView.clearSearchFocus()

            }



        })
    }
}