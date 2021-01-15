package com.exercise.movie_app.ui.detailmovie.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.databinding.FragmentInfoBinding
import com.exercise.movie_app.ui.movie.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment  : Fragment() {

    companion object {
        private const val ARG_ID = "id"
        fun getInstance(id: Int) : InfoFragment {
            return InfoFragment().apply {
                arguments = bundleOf(ARG_ID to id)
            }
        }
    }

    lateinit var binding : FragmentInfoBinding
    private var movieId : Int = 0
    private val infoViewModel : InfoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = arguments?.getInt(ARG_ID, 0)!!
        infoViewModel.fetchMovieDetail(movieId)
        infoViewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.apply {
                title = it.title
                vote = it.voteAvg + "/10"
                imdb = it.imdbId
                description = it.overview
                tagline = it.tagLine
                Glide.with(binding.root)
                    .load(BuildConfig.POSTER_URL + it.backdropPath)
                    .into(ivPoster)
            }
        })
    }


}