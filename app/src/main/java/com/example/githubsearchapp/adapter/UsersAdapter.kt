package com.example.githubsearchapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.Item
import com.example.githubsearchapp.views.MainActivity
import io.reactivex.disposables.CompositeDisposable


class UsersAdapter(private val usersList: List<Item>): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

   // private val compositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item_layout,parent,false)
        return UsersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        val user = usersList[position]

//        compositeDisposable.add(
//            MainActivity.viewModel.getSingleUser(user.login.toString())
//                .subscribe({user ->
//                    holder.repoCount.text = user.publicRepos.toString()
//                }, {throwable ->
//                    Log.d("ERROR_TAG", throwable.message.toString())
//                })
//        )

        holder.apply {

           username.text = user.login.toString()
            Glide.with(holder.itemView.context).load(user.avatarUrl).into(user_avatar)
           // repoCount.text = MainActivity.userRepoCount.toString()
        }

        holder.itemView.setOnClickListener {
            holder.itemView.context.applicationContext.sendBroadcast(Intent("from.user.recycler").also {
                it.putExtra("username", user.login.toString())
            })
        }

    }


    inner class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val username = itemView.findViewById<TextView>(R.id.username)
        val user_avatar = itemView.findViewById<ImageView>(R.id.user_avatar)
        val repoCount = itemView.findViewById<TextView>(R.id.repoCount_textView)

    }
}