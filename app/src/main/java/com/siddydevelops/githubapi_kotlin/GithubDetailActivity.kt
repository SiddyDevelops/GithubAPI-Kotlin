package com.siddydevelops.githubapi_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GithubDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_detail)

        val repoName = intent.getStringExtra(IntentProviders.REPO_NAME)
        val repoDesc = intent.getStringExtra(IntentProviders.REPO_DESC)
        val repoForks = intent.getIntExtra(IntentProviders.REPO_FORKS,0)
        val repoWatchers = intent.getIntExtra(IntentProviders.REPO_WATCHERS,0)

        val repoDetailName = findViewById<TextView>(R.id.repoDetailName)
        val repoDetailDescription = findViewById<TextView>(R.id.repoDetailDescription)
        val repoDetailForks = findViewById<TextView>(R.id.repoForks)
        val repoDetailWatchers = findViewById<TextView>(R.id.repoWatchers)

        repoDetailName.text = repoName
        repoDetailDescription.text = repoDesc
        repoDetailForks.text = repoForks.toString()
        repoDetailWatchers.text = repoWatchers.toString()

    }
}