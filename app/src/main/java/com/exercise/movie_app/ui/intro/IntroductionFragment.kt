package com.exercise.movie_app.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.exercise.movie_app.R
import com.exercise.movie_app.databinding.FragmentIntroductionBinding

class IntroductionFragment : Fragment() {

    private lateinit var binding : FragmentIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_introduction, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCheck.setOnClickListener {
            var errorMessage = "Your nickname must:\n"
            var error = false
            val nickname = binding.etName.text.toString()

            if(nickname.length < 6) {
                errorMessage += "- 6 characters\n"
                error = true
            }

            if(nickname != nickname.capitalize()) {
                errorMessage += "- 1 capital letter"
                error = true
            }

            if (error)
                binding.error = errorMessage
            else {
                val bundle = bundleOf("nickname" to nickname)
                Navigation.findNavController(view).navigate(R.id.action_introductionFragment_to_movieFragment,
                bundle,
                NavOptions.Builder().setPopUpTo(R.id.introductionFragment, true).build())
            }

        }
    }

    companion object {
        fun newInstance() = IntroductionFragment()
    }
}