package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.idat.foodie_app.NavbarFragment.AccountFragment
import com.idat.foodie_app.NavbarFragment.HomeFragment
import com.idat.foodie_app.NavbarFragment.RestaurantFragment
import com.idat.foodie_app.NavbarFragment.ShoppingCartFragment
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
                R.id.Inicio -> {
                    replaceFragment(HomeFragment())
                    binding.txtMainFragmentName.text = "Selección de restaurantes"
                }
                R.id.restaurantes -> {
                    replaceFragment(RestaurantFragment())
                    binding.txtMainFragmentName.text = "Menú del restaurante"
                }
                R.id.carrito -> {
                    replaceFragment(ShoppingCartFragment())
                    binding.txtMainFragmentName.text = "Carrito de compras"
                }
                R.id.cuenta -> {
                    replaceFragment(AccountFragment())
                    binding.txtMainFragmentName.text = "Perfil de usuario"
                }
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