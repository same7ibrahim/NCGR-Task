package com.blackstoneeit.ncgrtask.presentation.main.onenewsdetails

import android.annotation.SuppressLint
import android.webkit.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.presentation.base.BaseFragment
import com.blackstoneeit.ncgrtask.presentation.base.BaseViewModel
import com.blackstoneeit.ncgrtask.databinding.FragmentOneArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneArticleDetailsFragment : BaseFragment<FragmentOneArticleDetailsBinding>() {
    override fun onViewBound(binding: FragmentOneArticleDetailsBinding) {

        setupWebview()
    }

    private fun setupWebview() {
        binding.webView.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            databaseEnabled = true
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            defaultTextEncodingName = "utf-8"
            allowFileAccess = true
            allowContentAccess = true
        }

        binding.webView.webViewClient = object : WebViewClient() {
            @SuppressLint("WebViewClientOnReceivedSslError")
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.loading.isVisible = false
            }
        }

        arguments?.getString("articleLink")?.let {
            binding.webView.loadUrl(it)
        }

    }

    override val viewModel: BaseViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_one_article_details
}