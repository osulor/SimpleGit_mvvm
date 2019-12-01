package com.example.week6day2_gitapi_mvvm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week6day2_gitapi_mvvm.R
import com.example.week6day2_gitapi_mvvm.model.Repository

class RepoAdapter(private val repoList: List<Repository>): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item_layout,parent,false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentRepo = repoList.get(position)
        holder.apply {
            repoName.text = currentRepo.name.toString()
        }

        holder.itemView.setOnClickListener {
            holder.itemView.context.applicationContext.sendBroadcast(Intent("from.repo.recycler").also {
                it.putExtra("repositoryName", currentRepo.name)
            })
        }
    }


    inner class RepoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val repoName = itemView.findViewById<TextView>(R.id.repo_textView)
    }

}