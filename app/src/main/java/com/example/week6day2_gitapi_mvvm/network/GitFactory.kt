package com.example.week6day2_gitapi_mvvm.network

import com.example.week6day2_gitapi_mvvm.model.Repository
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    val BASE_URL = "https://api.github.com"
    private var gitService: GitService

    init {
        gitService = createService(getRetrofitInstance())
    }


    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createService(retrofit: Retrofit): GitService{
        return retrofit.create(GitService::class.java)
    }

    fun getRepositories(username: String): Observable<List<Repository>>{
       return gitService.getUsers(username)
    }

    fun getRepoInfos(username: String,repoName:String): Observable<Repository>{
        return gitService.getRepoInfos(username,repoName)
    }

}