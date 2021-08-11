package com.jitendraalekar.todo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jitendraalekar.todo.R
import com.jitendraalekar.todo.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = getString(R.string.app_name)
        binding.webview.loadUrl("file:///android_asset/about.html")  //android_asset points to assets
    }
}