package com.example.ejercicio_14.ViewPager

import android.app.Activity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ejercicio_14.*

class ViewPagerAdapter(fragment: Fragment, val actividad: Activity) : FragmentStateAdapter(fragment) {

    var ide: Int = 2
    var bundle: Bundle = bundleOf("tematica" to "libro")

    override fun createFragment(position: Int): Fragment {


        when (position){
            0 -> return FourthFragment()
            //1 -> return ThirdFragment()
            //2 -> return SecondFragment()
            1 -> (actividad as MainActivity).findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_viewPagerFragment_to_thirdFragment2, bundle)
        }
        return FirstFragment()
    }

    override fun getItemCount(): Int = 2

}