package com.example.week6day2_gitapi_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.week6day2_gitapi_mvvm.R
import com.example.week6day2_gitapi_mvvm.model.Repository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_repo_infos_.*

class RepoInfos_Activity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_infos_)

        val repoName = intent?.getStringExtra("repositoryName").toString()

        compositeDisposable.add(
            MainActivity.viewModel.getRepoInfos("osulor",repoName)
                .subscribe({repository ->
                    displayInfos(repository)
                }, {throwable ->

                    Log.d("ERROR_TAG", throwable.message.toString())
                })
        )

    }


    fun displayInfos(repository: Repository){

        Glide.with(this).load(repository.owner.avatarUrl).into(github_avatar)
        repoName_textView.text = "Repository name: " + repository.name
        githubRepo_link.text = "Repository link: " + repository.htmlUrl.toString()
        language_textView.text = "Language Used: " +  repository.language
        creationDate_textView.text = "Created on : " + repository.createdAt.toString()
        lastUpdate_textView.text = "Last Upadated n " + repository.updatedAt.toString()
    }

}
