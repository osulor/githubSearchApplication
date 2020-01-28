package com.example.githubsearchapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearchapp.R
import com.example.githubsearchapp.model.Repository
import com.example.githubsearchapp.views.ReposWebviewActivity

class ReposAdapter(private val repoList: List<Repository>) : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_layout,parent,false)

        return RepoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {

            val repo = repoList[position]

            holder.apply {
                refreshFields()
                repoName.text = repo.name.toString()
                forkCount.append(repo.forksCount.toString())
                starsCount.append(repo.stargazersCount.toString())
            }

//        holder.itemView.setOnClickListener {
//            val webView : WebView = holder.itemView.findViewById(R.id.webView)
//
//            webView.loadUrl(repo.htmlUrl)
//            webView.visibility = View.VISIBLE
//        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, ReposWebviewActivity::class.java)
            intent.putExtra("repositoryUrl", repoList[position].htmlUrl)
            it.context.startActivity(intent)
        }

    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val repoName : TextView = itemView.findViewById(R.id.repo_name)
        val forkCount : TextView = itemView.findViewById(R.id.fork)
        val starsCount : TextView = itemView.findViewById(R.id.stars)


        fun refreshFields(){
            forkCount.text = "Forks: "
            starsCount.text = "Stars: "
        }
    }

}