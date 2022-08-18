package com.aliasadi.clean.di.core.module

import com.aliasadi.data.api.MovieApi
import com.aliasadi.data.db.MovieDao
import com.aliasadi.data.repository.movie.*
import com.aliasadi.data.util.DiskExecutor
import com.aliasadi.data.util.DispatchersProvider
import com.aliasadi.domain.repository.MovieRepository
import com.aliasadi.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ali Asadi on 15/05/2020
 **/
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemote: MovieDataSource.Remote,
        movieLocal: MovieDataSource.Local,
        movieCache: MovieDataSource.Cache
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal, movieCache)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor, movieDao: MovieDao
    ): MovieDataSource.Local {
        return MovieLocalDataSource(executor, movieDao)
    }

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(diskExecutor: DiskExecutor): MovieDataSource.Cache {
        return MovieCacheDataSource(diskExecutor)
    }


    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi, dispatchers: DispatchersProvider): MovieDataSource.Remote {
        return MovieRemoteDataSource(movieApi, dispatchers)
    }

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovies {
        return GetMovies(movieRepository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(movieRepository: MovieRepository): GetMovieDetails {
        return GetMovieDetails(movieRepository)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(movieRepository: MovieRepository): GetFavoriteMovies {
        return GetFavoriteMovies(movieRepository)
    }

    @Provides
    fun provideCheckFavoriteStatusUseCase(movieRepository: MovieRepository): CheckFavoriteStatus {
        return CheckFavoriteStatus(movieRepository)
    }

    @Provides
    fun provideAddMovieToFavoriteUseCase(movieRepository: MovieRepository): AddMovieToFavorite {
        return AddMovieToFavorite(movieRepository)
    }

    @Provides
    fun provideRemoveMovieFromFavoriteUseCase(movieRepository: MovieRepository): RemoveMovieFromFavorite {
        return RemoveMovieFromFavorite(movieRepository)
    }
}