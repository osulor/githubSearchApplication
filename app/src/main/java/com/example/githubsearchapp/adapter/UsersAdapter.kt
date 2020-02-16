package com.example.githubsearchapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearchapp.R
import com.example.githubsearchapp.databinding.UserItemLayoutBinding
import com.example.githubsearchapp.model.Item
import com.example.githubsearchapp.views.MainActivity
import io.reactivex.disposables.CompositeDisposable


class UsersAdapter(private val usersList: List<Item>): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

   // private val compositeDisposable = CompositeDisposable()
    private lateinit var user : Item


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {

        return UsersViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.user_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

         user = usersList[position]

         holder.binding.user = user

        holder.binding.root.setOnClickListener {
            holder.binding.root.context.sendBroadcast(Intent("from.user.recycler")
                .also {
                    it.putExtra("username", user.login.toString())
            })
        }
    }

    inner class UsersViewHolder(val binding: UserItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
}