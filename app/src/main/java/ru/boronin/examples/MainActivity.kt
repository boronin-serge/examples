package ru.boronin.examples

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupTabView()
    }

    private fun setupTabView() {

        customTabLayout.getTabAt(0)?.setCustomView(R.layout.tab_item)
        customTabLayout.getTabAt(1)?.setCustomView(R.layout.tab_item)
        customTabLayout.getTabAt(3)?.setCustomView(R.layout.tab_item)

        for (i in 0..customTabLayout.tabCount) {

            customTabLayout.getTabAt(i)?.setCustomView(R.layout.tab_item)
            val tabName = customTabLayout?.getTabAt(i)?.customView?.findViewById<TextView>(R.id.tabName)
            tabName?.text = "Tab"
        }
    }
}
