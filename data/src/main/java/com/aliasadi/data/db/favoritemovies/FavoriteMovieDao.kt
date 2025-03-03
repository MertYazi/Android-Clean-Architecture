package com.aliasadi.data.db.favoritemovies

import androidx.paging.PagingSource
import androidx.room.*
import com.aliasadi.data.entities.FavoriteMovieDbData
import com.aliasadi.data.entities.MovieDbData

/**
 * @author by Ali Asadi on 22/08/2022
 */
@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAll(): List<FavoriteMovieDbData>

    @Query("SELECT * FROM movies where id in (SELECT movieId FROM favorite_movies)")
    fun favoriteMovies(): PagingSource<Int, MovieDbData>

    @Query("SELECT * FROM favorite_movies where movieId=:movieId")
    fun get(movieId: Int): FavoriteMovieDbData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(favoriteMovieDbData: FavoriteMovieDbData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(favoriteMovieDbData: List<FavoriteMovieDbData>)

    @Query("DELETE FROM favorite_movies WHERE movieId=:movieId")
    fun remove(movieId: Int)

}