package com.example.githubsearchapp.network

import com.example.githubsearchapp.model.Item
import com.example.githubsearchapp.model.Repository
import com.example.githubsearchapp.model.SingleUser
import com.example.githubsearchapp.model.Users
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    val BASE_URL = "https://api.github.com"
    private var gitService: GitService

    init {
        gitService = createService(getRetrofitInstance())
    }


    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createService(retrofit: Retrofit): GitService{
        return retrofit.create(GitService::class.java)
    }

    fun getUsersList(username: String): Single<Users> {
        return gitService.getUsers(username)
    }

    fun getRepoInfos(username: String,repoName:String): Single<Repository> {
        return gitService.getRepoInfos(username,repoName)
    }

    fun getRepos(username: String) : Single <List<Repository>>{
        return gitService.getRepos(username)
    }

    fun getSingleUser(username: String) : Single <SingleUser>{
        return gitService.getSingleUser(username)
    }

}