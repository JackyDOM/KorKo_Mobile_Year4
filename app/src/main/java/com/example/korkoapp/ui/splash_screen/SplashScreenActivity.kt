package com.example.korkoapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.korkoapp.R
import com.example.korkoapp.ui.element.Activity.MainActivityButtonNavBar

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var mSLideViewPager: ViewPager
    private lateinit var mDotLayout: LinearLayout
    private lateinit var backbtn: Button
    private lateinit var nextbtn: Button
    private lateinit var skipbtn: Button

    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        backbtn = findViewById(R.id.backbtn)
        nextbtn = findViewById(R.id.nextbtn)
        skipbtn = findViewById(R.id.skipButton)

        backbtn.setOnClickListener {
            if (getitem(0) > 0) {
                mSLideViewPager.setCurrentItem(getitem(-1), true)
            }
        }

        nextbtn.setOnClickListener {
            if (getitem(0) < 3) {
                mSLideViewPager.setCurrentItem(getitem(1), true)
            } else {
                val intent = Intent(this@SplashScreenActivity, MainActivityButtonNavBar::class.java)
                startActivity(intent)
                finish()
            }
        }

        skipbtn.setOnClickListener {
            val intent = Intent(this@SplashScreenActivity, MainActivityButtonNavBar::class.java)
            startActivity(intent)
            finish()
        }

        mSLideViewPager = findViewById(R.id.slideViewPager)
        mDotLayout = findViewById(R.id.indicator_layout)

        viewPagerAdapter = ViewPagerAdapter(this)

        mSLideViewPager.adapter = viewPagerAdapter

        setUpIndicator(0)
        mSLideViewPager.addOnPageChangeListener(viewListener)

    }

    private fun setUpIndicator(position: Int) {
        dots = arrayOfNulls(4)
        mDotLayout.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226")
                textSize = 35f
                setTextColor(ContextCompat.getColor(this@SplashScreenActivity, R.color.inactive))
            }
            mDotLayout.addView(dots[i])
        }

        dots[position]?.setTextColor(ContextCompat.getColor(this, R.color.active))
    }

    private val viewListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageSelected(position: Int) {
            setUpIndicator(position)

            backbtn.visibility = if (position > 0) View.VISIBLE else View.INVISIBLE
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }

    private fun getitem(i: Int): Int {
        return mSLideViewPager.currentItem + i
    }

}