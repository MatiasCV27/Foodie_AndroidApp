package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.idat.foodie_app.databinding.ActivityMenuPrincipalBinding


class MenuPrincipal : AppCompatActivity() {
    private lateinit var binding : ActivityMenuPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.BottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.Inicio -> replaceFragment(HomeFragment())
                R.id.restaurantes -> replaceFragment(RestaurantFragment())
                R.id.carrito -> replaceFragment(ShoppingCartFragment())
                R.id.cuenta -> replaceFragment(AccountFragment())

                else ->{



                }

            }

            true

        }


    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()


    }
}