package com.example.githubsearchapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.githubsearchapp.R

class ReposWebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos_webview)


        val intent = intent

        val repoWeb = findViewById<WebView>(R.id.webview)

        repoWeb.loadUrl(intent.getStringExtra("repositoryUrl"))
    }
}
