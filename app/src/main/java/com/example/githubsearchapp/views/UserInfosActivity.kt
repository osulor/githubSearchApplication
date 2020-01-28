package com.example.githubsearchapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubsearchapp.R
import com.example.githubsearchapp.adapter.ReposAdapter
import com.example.githubsearchapp.model.Repository
import com.example.githubsearchapp.model.SingleUser
import com.example.githubsearchapp.views.MainActivity.Companion.compositeDisposable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_user_infos.*

class UserInfosActivity : AppCompatActivity() {

    //private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_infos)

        val username = intent?.getStringExtra("username").toString()

      compositeDisposable.add(
            MainActivity.viewModel.getRepositories(username)
                .subscribe({repository ->
                    displayInfos(repository)

                }, {throwable ->

                    Log.d("ERROR_TAG", throwable.message.toString())
                })
        )

        compositeDisposable.add(
            MainActivity.viewModel.getSingleUser(username)
                .subscribe({user ->
                    displayUserInfos(user)
                }, {throwable ->

                    Log.d("ERROR_TAG", throwable.message.toString())
                })

        )

        search_repos.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               // val searchValue = search_user.text.toString()

                repoList_listView.adapter?.notifyDataSetChanged()
                compositeDisposable.add(
                    MainActivity.viewModel.getRepositories(username)
                        .subscribe({repository ->
                            displayInfos(repository)

                        }, {throwable ->

                            Log.d("ERROR_TAG", throwable.message.toString())
                        })
                )



            }
        })

    }

    fun displayInfos(repositories: List<Repository>){

        val adapter = ReposAdapter(repositories)
        repoList_listView.adapter = adapter

        repoList_listView.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        repoList_listView.addItemDecoration(itemDecoration)

    }


    fun displayUserInfos(user: SingleUser){

        Glide.with(this).load(user.avatarUrl).into(userInfo_avatar)
        userInfo_name.text =  user.login.toString()
        joinDate.append(user.createdAt.toString().dropLast(10))


        if (user.following.toString().isNotEmpty()) {
            following.append(user.following.toString())
        }

        if (user.followers.toString().isNotEmpty()) {
            followers.append( user.followers.toString())

        }

        if (user.location == null ) {
            following.append("N/A")
        } else {

            userLocation.append(user.location.toString())
        }

        if (user.email == null ) {
            userEmail.append("N/A")
        } else {
            following.append(user.email.toString())
        }

        if (user.bio != null){
            user_bio.text = user.bio.toString()
        } else {
            user_bio.text = "N/A"
        }

    }
}
