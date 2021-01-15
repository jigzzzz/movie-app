package com.exercise.movie_app.ui.movie

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.exercise.movie_app.R
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.databinding.FragmentMovieBinding
import com.exercise.movie_app.external.adapter.GridAdapter
import com.exercise.movie_app.external.adapter.SliderAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel : MovieViewModel by viewModel()
    lateinit var binding : FragmentMovieBinding
    lateinit var nickname: String
    private val slideHandler : Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nickname = arguments?.getString("nickname")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Popular Movie Carousel Adapter
        val sliderAdapter = SliderAdapter()
        movieViewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            sliderAdapter.setMovies(it)
        })
        sliderAdapter.setItemClickListener(object : SliderAdapter.OnItemClickListener{
            override fun onClick(movie: SlideMovie) {
                val bundle = bundleOf("id" to movie.id)
                Navigation.findNavController(binding.root).navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)

            }
        })

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            var r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        // Upcoming Movie Adapter
        val gridAdapter = GridAdapter()
        movieViewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { gridAdapter.setMovies(it) })
        gridAdapter.setItemClickListener(object : GridAdapter.OnItemClickListener{
            override fun onClick(movie: Movie) {
                val bundle = bundleOf("id" to movie.id)
                Navigation.findNavController(binding.root).navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
            }
        })

        binding.apply {
            name = nickname
            vpMovie.apply {
                adapter = sliderAdapter
                setPageTransformer(compositePageTransformer)
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        slideHandler.removeCallbacks(slideRunnable)
                        slideHandler.postDelayed(slideRunnable, 3000)
                    }
                })
            }
            rvUpcomingMovie.apply {
                adapter = gridAdapter
                layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

    private val slideRunnable = Runnable {
        binding.vpMovie.apply {
            currentItem += 1
        }
    }
}