package com.example.skillcinema.presentation.onBoarding

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.skillcinema.R
import com.example.skillcinema.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    private lateinit var dots: Array<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter()
        setDotIndicator(0)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                setDotIndicator(position)
            }
        })

    }

    fun setDotIndicator(position: Int) {

        dots = Array<TextView>(3) {
            TextView(context)
            TextView(context)
            TextView(context)
        }

        binding.linearLayoutIndicator.removeAllViews()

        for (i in dots.indices) {
            dots[i].text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
            dots[i].textSize = 35.0F
            dots[i].setTextColor(resources.getColor(R.color.grey, resources.newTheme()))
            binding.linearLayoutIndicator.addView(dots[i])
        }

        dots[position].setTextColor(resources.getColor(R.color.black, resources.newTheme()))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}