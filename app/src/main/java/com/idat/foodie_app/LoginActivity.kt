package com.idat.foodie_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Utils.SelectedUserStats

class LoginActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnIniciarS = findViewById<Button>(R.id.btnSesion)
        val btnIniciarG = findViewById<Button>(R.id.btnSGoogle)
        val btnReistro = findViewById<Button>(R.id.btnRegistrase)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtpassw = findViewById<TextInputEditText>(R.id.txtPassword)

        btnIniciarS.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtpassw.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "FOODIE: Los campos deben estar llenos para el acceso", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        db.collection("users").document(email).get().addOnSuccessListener {
                            val nombre = it.get("nombre") as String?
                            val intent = Intent(this, MenuPrincipal::class.java)
                            intent.putExtra("nombre", nombre)
                            startActivity(intent)
                        }
                        showMenuPrincipal(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        Toast.makeText(this, "FOODIE: Se ha producido un error autenticando al usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnIniciarG.setOnClickListener{
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1018100386893-19s67cg3lrh05de2mjqpt1i359scjbb0.apps.googleusercontent.com")
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }

        btnReistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        session()
    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)
        if (email != null && provider != null) {
            showMenuPrincipal(email, ProviderType.valueOf(provider))
        }
    }

    private fun showMenuPrincipal(email: String, provider: ProviderType) {
        val menuPrincipalintent = Intent(this, MenuPrincipal::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(menuPrincipalintent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val email = account.email ?: ""
                                val username = account.displayName?.split(" ")?.get(0) ?: ""
                                db.collection("users").document(email).set(
                                    hashMapOf("email" to email,
                                        "nombre" to username,
                                        "proveedor" to ProviderType.GOOGLE)
                                )
                                db.collection("users").document(email).get().addOnSuccessListener {
                                    SelectedUserStats.emailUser = it.get("email") as String
                                    val nombre = it.get("nombre") as String?
                                    val intent = Intent(this, MenuPrincipal::class.java)
                                    intent.putExtra("nombre", nombre)
                                    startActivity(intent)
                                }
                                showMenuPrincipal(account.email ?: "", ProviderType.GOOGLE)
                            } else {
                                Toast.makeText(this, "FOODIE: Se ha producido un error autenticando al usuario", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "FOODIE: Se ha producido un error autenticando al usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}