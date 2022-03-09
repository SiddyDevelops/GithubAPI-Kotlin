package com.siddydevelops.githubapi_kotlin.RV

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import com.siddydevelops.githubapi_kotlin.R

class RVAdapter(private val githubList: List<GithubDetailModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.github_layout_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val githubItem = githubList[position]
        holder.repoName.text = githubItem.name
        holder.repoDescription.text = githubItem.description
    }

    override fun getItemCount(): Int {
        return githubList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var repoName: TextView = itemView.findViewById(R.id.repoName)
        val repoDescription: TextView = itemView.findViewById(R.id.repoDescription)
    }

}