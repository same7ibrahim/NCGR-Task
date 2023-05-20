package com.blackstoneeit.ncgrtask.presentation.main

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.blackstoneeit.ncgrtask.R
import com.blackstoneeit.ncgrtask.presentation.base.BaseActivity
import com.blackstoneeit.ncgrtask.presentation.base.BaseViewModel
import com.blackstoneeit.ncgrtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onViewBound(binding: ActivityMainBinding) {

        setupNavHostAndController()
    }

    private fun setupNavHostAndController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

    }


    override val viewModel: BaseViewModel by viewModels()
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun provideLoadingView(): View {
        return super.provideLoadingView()
    }
}