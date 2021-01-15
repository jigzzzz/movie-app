package com.exercise.movie_app.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.exercise.movie_app.R
import com.exercise.movie_app.ui.detailmovie.comment.CommentFragment
import com.exercise.movie_app.ui.detailmovie.info.InfoFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class TabAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var movieId : Int = 0

    fun setMovieId(id: Int) {
        movieId = id
        Log.d("<DEBUG>SET", movieId.toString())
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> { InfoFragment.getInstance(movieId) }
            1 -> { CommentFragment.getInstance(movieId) }
            else -> { InfoFragment.getInstance(movieId) }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

}