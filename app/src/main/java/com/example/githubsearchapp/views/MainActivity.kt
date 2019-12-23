package com.example.githubsearchapp.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchapp.R
import com.example.githubsearchapp.adapter.UsersAdapter
import com.example.githubsearchapp.model.Item
import com.example.githubsearchapp.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_item_layout.*

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var viewModel: GitViewModel
        val compositeDisposable = CompositeDisposable()
    }

    val handler = Handler()

    val myReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            handler.post{
                val username = intent?.getStringExtra("username")
                val intent2 = Intent(context,UserInfosActivity::class.java)
                intent2.putExtra("username",username)
                startActivity(intent2)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(myReceiver, IntentFilter("from.user.recycler"))
        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)


        search_user.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val searchValue = search_user.text.toString()


                compositeDisposable.add(
                    viewModel.getUsers(searchValue)
                        .subscribe({users ->
                            displayUsers(users.items)

                        }, {throwable ->
                            Log.d("ERROR_TAG", throwable.message.toString())
                        })
                )
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

//                val searchValue = search_user.text.toString()
//
//
//                compositeDisposable.add(
//                    viewModel.getUsers(searchValue)
//                        .subscribe({users ->
//                            displayUsers(users.items)
//
//                        }, {throwable ->
//                            Log.d("ERROR_TAG", throwable.message.toString())
//                        })
//                )
            }
        })
    }

    fun displayUsers(usersList: List<Item>){

        val adapter = UsersAdapter(usersList)
        users_listView.adapter = adapter
        users_listView.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        users_listView.addItemDecoration(itemDecoration)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }


}
