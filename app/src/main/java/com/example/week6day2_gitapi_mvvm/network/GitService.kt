package com.example.week6day2_gitapi_mvvm.network

import com.example.week6day2_gitapi_mvvm.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("users/{username}/repos")
    fun getUsers(@Path("username") username: String): Observable<List<Repository>>

    @GET("repos/{username}/{repoName}")
    fun getRepoInfos(@Path("username") username: String,
                    @Path("repoName") repoName: String) : Observable<Repository>


}