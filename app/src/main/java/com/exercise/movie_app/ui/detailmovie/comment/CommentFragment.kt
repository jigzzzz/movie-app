package com.exercise.movie_app.ui.detailmovie.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.movie_app.databinding.FragmentCommentBinding
import com.exercise.movie_app.databinding.FragmentInfoBinding
import com.exercise.movie_app.external.adapter.CommentAdapter
import com.exercise.movie_app.ui.detailmovie.info.InfoFragment
import com.exercise.movie_app.ui.detailmovie.info.InfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentFragment  : Fragment() {

    companion object {
        private const val ARG_ID = "id"
        fun getInstance(id: Int) : CommentFragment {
            return CommentFragment().apply {
                arguments = bundleOf(ARG_ID to id)
            }
        }
    }

    lateinit var binding : FragmentCommentBinding
    private var movieId : Int = 0
    private val commentViewModel : CommentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getInt(ARG_ID)!!
        commentViewModel.fetchMovieReviews(movieId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentBinding.inflate(inflater, container, false)

        val commentAdapter = CommentAdapter()
        commentViewModel.comments.observe(viewLifecycleOwner, Observer {
            commentAdapter.setReviews(it)
        })

        binding.rvComments.apply {
            adapter = commentAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }
}