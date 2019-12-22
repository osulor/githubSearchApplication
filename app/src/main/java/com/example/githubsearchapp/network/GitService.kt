package com.example.githubsearchapp.network

import com.example.githubsearchapp.model.Item
import com.example.githubsearchapp.model.Repository
import com.example.githubsearchapp.model.SingleUser
import com.example.githubsearchapp.model.Users
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitService {

    @GET("/search/users")
    fun getUsers(@Query("q") username: String): Single<Users>




    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Single<List<Repository>>

    @GET("/users/{username}/{repoName}")
    fun getRepoInfos(@Path("username") username: String,
                     @Path("repoName") repoName: String): Single<Repository>



    @GET("/users/{username}")
    fun getSingleUser(@Path("username") username: String): Single<SingleUser>


}