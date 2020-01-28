package com.example.githubsearchapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.githubsearchapp.model.Repository
import com.example.githubsearchapp.model.SingleUser
import com.example.githubsearchapp.model.Users
import com.example.githubsearchapp.network.GitFactory
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GitViewModel: ViewModel(){

    val gitFactory = GitFactory()

    fun getRepositories(username: String): Single<List<Repository>> {
        return gitFactory.getRepos(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepoInfos(username: String, repoName: String): Single<Repository> {
        return gitFactory.getRepoInfos(username,repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUsers(username: String) : Single<Users> {
        return gitFactory.getUsersList(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

//    fun getUsers(username: String) : Single<Users> {
//        return gitFactory.getUsersList(username)
//            .flatMap { users ->
//                users.items.forEach {
//                    gitFactory.getRepos(it.login)
//                        .flatMap { repos ->
//                            users.totalNumberOfRepositories = repos.size
//                            Single.just(repos)
//                        }.doOnError { it.printStackTrace() }
//                }
//                Single.just(users)
//            }
//            .doOnError { it.printStackTrace() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }


    fun getSingleUser(username: String) : Single<SingleUser> {
        return gitFactory.getSingleUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}