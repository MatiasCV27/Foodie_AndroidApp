package com.idat.foodie_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.idat.foodie_app.NavbarFragment.AccountFragment
import com.idat.foodie_app.NavbarFragment.HomeFragment
import com.idat.foodie_app.NavbarFragment.RestaurantFragment
import com.idat.foodie_app.NavbarFragment.ShoppingCartFragment
import com.idat.foodie_app.databinding.ActivityMenuPrincipalBinding
import com.idat.foodie_app.databinding.FragmentRestTodoBinding

enum class ProviderType {
    BASIC,
    GOOGLE
}

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

        val imgUser = findViewById<ImageView>(R.id.imgUserIconGlobal)
        val setUserName = findViewById<TextView>(R.id.imgtextuserfrag)

        val nombre = intent.getStringExtra("nombre")
        setUserName.text = nombre

        imgUser.setOnClickListener {
            val fragment = AccountFragment()
            replaceFragment(fragment)
            binding.txtMainFragmentName.text = "Perfil de usuario"
        }

        //Todo: Setup
        val bundle = intent.extras
        val email =  bundle?.getString("email")
        val provider =  bundle?.getString("provider")

        //Todo: Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    fun getBundleData(): Bundle? {
        val intent = Intent()
        val bundle = intent.extras
        val provider = bundle?.getString("provider")
        return bundle
    }

}