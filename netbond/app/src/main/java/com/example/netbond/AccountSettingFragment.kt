package com.example.netbond

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.netbond.services.StorageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountSettingFragment : Fragment(R.layout.activity_account_settings) {

    private val db = StorageService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_account_setting, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getUserData()

        val buttonClick = view.findViewById<Button>(R.id.btn_save_settings)
        buttonClick.setOnClickListener {
            updateUserData()
            // Action
//            findNavController().navigate(R.id.action_id_settings_to_user_profile)
//            val intent = Intent(this, UserProfileActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun getUserData() {

        val thisUsername = "johndoe"// getThisUsername()

        // El botón debería tener la imagen como fondo
        // val imgProfile = findViewById<ImageView>(R.id.img_profile)
        val editName = view?.findViewById<TextView>(R.id.edit_name)
        val editUsername = view?.findViewById<TextView>(R.id.edit_username)
        val editEmail = view?.findViewById<TextView>(R.id.edit_email)

        // imgProfile.setImageURI(user.profile_image)
        // Glide.with(this).load(user.profile_image).into(imgProfile)
        // Picasso.get().load(user.profile_image).into(imgProfile)

        CoroutineScope(Dispatchers.Main).launch{
            var user = db.getUser(thisUsername)

            editUsername?.text = thisUsername
            if (user != null) {
                editName?.text = user.name
                editEmail?.text = user.email
            }
        }
    }


    private fun updateUserData() {

        val thisUsername = "johndoe"// getThisUsername()

        // El botón debería tener la imagen como fondo
        // val imgProfile = findViewById<ImageView>(R.id.img_profile)
        val editName = view?.findViewById<TextView>(R.id.edit_name)
        val editUsername = view?.findViewById<TextView>(R.id.edit_username)
        val editEmail = view?.findViewById<TextView>(R.id.edit_email)

        // imgProfile.setImageURI(user.profile_image)
        // Glide.with(this).load(user.profile_image).into(imgProfile)
        // Picasso.get().load(user.profile_image).into(imgProfile)

        CoroutineScope(Dispatchers.Main).launch{
            var user = db.getUser(thisUsername)

            if (user != null) {
                user.name = editName?.text.toString()
                user.username = editUsername?.text.toString()
            }
            db.updateUser(user)
        }

    }
}