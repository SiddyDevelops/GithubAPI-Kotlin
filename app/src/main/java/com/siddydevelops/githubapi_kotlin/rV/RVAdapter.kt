package com.siddydevelops.githubapi_kotlin.rV

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siddydevelops.githubapi_kotlin.apiModel.GithubDetailModel
import com.siddydevelops.githubapi_kotlin.databinding.GithubLayoutItemBinding

class RVAdapter(private var githubList: List<GithubDetailModel>) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val githubAdapterViewBinding = GithubLayoutItemBinding.inflate(inflater, parent,false)
        return ViewHolder(githubAdapterViewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val githubItem = githubList[position]
        holder.bind(githubItem)
//        holder.repoName.text = githubItem.name
//        holder.repoDescription.text = githubItem.description
//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context,GithubDetailActivity::class.java)
//            intent.putExtra(IntentProviders.REPO_NAME,githubItem.name)
//            intent.putExtra(IntentProviders.REPO_DESC,githubItem.description)
//            intent.putExtra(IntentProviders.REPO_FORKS,githubItem.forks)
//            intent.putExtra(IntentProviders.REPO_WATCHERS,githubItem.watchers)
//            holder.itemView.context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return githubList.size
    }

    fun setGithubItems(githubItemList: List<GithubDetailModel>) {
        Log.d("DATA->", githubItemList.toString())
        this.githubList = githubItemList.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: GithubLayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubDetailModel) {
            binding.githubItem = item
        }
//        var repoName: TextView = itemView.findViewById(R.id.repoName)
//        val repoDescription: TextView = itemView.findViewById(R.id.repoDescription)

    }

}