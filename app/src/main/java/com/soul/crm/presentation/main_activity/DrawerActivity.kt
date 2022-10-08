package com.soul.crm.presentation.main_activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.soul.crm.R
import com.soul.crm.databinding.ActivityDrawerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarDrawer.toolbar)
//        supportActionBar?.setIcon(R.drawable.menu_icon_white)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)

        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.menu_icon_white)
        }
//        supportActionBar?.title = Html.fromHtml("<font color = \"red\">"+getString(R.string.app_name))
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.dashBoardFragment,
                R.id.signUpFragment,
                R.id.teachersFragment,
                R.id.studentsFragment,
                R.id.groupsFragment,
                R.id.usersFragment,
                R.id.attendanceFragment,
                R.id.paymentFragment,
                R.id.coursesFragment,
                R.id.scheduleFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}