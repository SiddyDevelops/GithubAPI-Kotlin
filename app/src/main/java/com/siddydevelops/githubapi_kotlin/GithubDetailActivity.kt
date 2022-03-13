package com.siddydevelops.githubapi_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.siddydevelops.githubapi_kotlin.databinding.ActivityGithubDetailBinding

class GithubDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repoName = intent.getStringExtra(IntentProviders.REPO_NAME)
        val repoDesc = intent.getStringExtra(IntentProviders.REPO_DESC)
        val repoForks = intent.getIntExtra(IntentProviders.REPO_FORKS,0)
        val repoWatchers = intent.getIntExtra(IntentProviders.REPO_WATCHERS,0)

        binding.repoDetailName.text = repoName
        binding.repoDetailDescription.text = repoDesc
        binding.repoForks.text = repoForks.toString()
        binding.repoWatchers.text = repoWatchers.toString()

    }
}