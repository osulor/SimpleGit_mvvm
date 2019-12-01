package com.example.week6day2_gitapi_mvvm.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week6day2_gitapi_mvvm.R
import com.example.week6day2_gitapi_mvvm.adapter.RepoAdapter
import com.example.week6day2_gitapi_mvvm.model.Repository
import com.example.week6day2_gitapi_mvvm.viewmodel.GitViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    companion object{
        lateinit var viewModel: GitViewModel
    }

    val compositeDisposable = CompositeDisposable()
    val handler = Handler()


    val myReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            handler.post{
                val repoName = intent?.getStringExtra("repositoryName")
                val intent2 = Intent(context,RepoInfos_Activity::class.java)
                intent2.putExtra("repositoryName",repoName)
                startActivity(intent2)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(myReceiver, IntentFilter("from.repo.recycler"))
        viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)

        compositeDisposable.add(
            viewModel.getRepositories("osulor")
                .subscribe({repositories ->
                    displayRepositories(repositories)
                }, {throwable ->

                    Log.d("ERROR_TAG", throwable.message.toString())
                })
        )
    }


    fun displayRepositories(repositories: List<Repository>){
        val adapter = RepoAdapter(repositories)
        repos_listView.adapter = adapter
        repos_listView.layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        repos_listView.addItemDecoration(itemDecoration)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}
