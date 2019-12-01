package com.example.week6day2_gitapi_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week6day2_gitapi_mvvm.model.Repository
import com.example.week6day2_gitapi_mvvm.network.GitFactory
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitViewModel: ViewModel() {

    val gitFactory = GitFactory()

    fun getRepositories(username: String): Observable<List<Repository>>{
       return gitFactory.getRepositories(username)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRepoInfos(username: String, repoName: String): Observable<Repository>{
        return gitFactory.getRepoInfos(username,repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}