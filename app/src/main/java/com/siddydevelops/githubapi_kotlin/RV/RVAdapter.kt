package com.siddydevelops.githubapi_kotlin.RV

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.siddydevelops.githubapi_kotlin.ApiModel.GithubDetailModel
import com.siddydevelops.githubapi_kotlin.GithubDetailActivity
import com.siddydevelops.githubapi_kotlin.IntentProviders
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
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context,GithubDetailActivity::class.java)
            intent.putExtra(IntentProviders.REPO_NAME,githubItem.name)
            intent.putExtra(IntentProviders.REPO_DESC,githubItem.description)
            intent.putExtra(IntentProviders.REPO_FORKS,githubItem.forks)
            intent.putExtra(IntentProviders.REPO_WATCHERS,githubItem.watchers)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return githubList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var repoName: TextView = itemView.findViewById(R.id.repoName)
        val repoDescription: TextView = itemView.findViewById(R.id.repoDescription)
    }

}