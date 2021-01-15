package com.exercise.movie_app.di

import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.data.repository.MovieRepository
import com.exercise.movie_app.data.repository.MovieRepositoryImpl
import com.exercise.movie_app.data.repository.remote.MovieService
import com.exercise.movie_app.ui.detailmovie.comment.CommentViewModel
import com.exercise.movie_app.ui.detailmovie.info.InfoViewModel
import com.exercise.movie_app.ui.movie.MovieViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { InfoViewModel(get()) }
    viewModel { CommentViewModel(get()) }
}

val serviceModule = module {
    fun provideMovieService(retrofit: Retrofit) : MovieService = retrofit.create(MovieService::class.java)

    single { provideMovieService(get()) }
}

val netModule = module {
    fun provideOkHttp() = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()

    fun provideGson() = GsonConverterFactory.create()

    fun provideRetrofit(gson : GsonConverterFactory, client : OkHttpClient) = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gson)
            .client(client)
            .build()

    single { provideOkHttp() }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
}