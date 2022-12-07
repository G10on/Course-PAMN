package com.example.netbond

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.netbond.controllers.BondController
import com.example.netbond.services.StorageService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BondCreationFragment : Fragment(R.layout.activity_bond_creation) {

    private val db = StorageService()
    private val bondController = BondController()
    private val currentUsername = "johndoe"// getExternalUsername()
    private var quest: String = ""
    private var ansList = ArrayList<String>()
    private var rightView:View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.activity_bond_creation, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFields()

        val buttonClick = view.findViewById<Button>(R.id.btn_create_bond)
        buttonClick.setOnClickListener {
            // Action
//            val intent = Intent(this, UserProfileActivity::class.java)
//            startActivity(intent)
        }
    }

    fun setFields() {

        val btnAddWrong = requireView().findViewById<Button>(R.id.btn_add_wrong_ans)
        val lvWrongAns = requireView().findViewById<ListView>(R.id.lv_wrong_ans)
        val btnCreateBond = requireView().findViewById<ListView>(R.id.btn_create_bond)

        val editQuestion = view?.findViewById<TextView>(R.id.edit_question)
        val editWrongAns = view?.findViewById<TextView>(R.id.edit_wrong_ans)
        val editRightAns = view?.findViewById<TextView>(R.id.edit_right_ans)

        // imgProfile.setImageURI(user.profile_image)
        // Glide.with(this).load(user.profile_image).into(imgProfile)
        // Picasso.get().load(user.profile_image).into(imgProfile)

        btnAddWrong?.setOnClickListener{
//            ansList.put(idQuest.plus(1),
//                Pair(false, editWrongAnsList?.text.toString()))
//            editRightAns?.text = ansList.last()

            if (editWrongAns != null && !editWrongAns.text.isNullOrEmpty()) {
                ansList.add(editWrongAns.text.toString())
                editWrongAns.text = ""
                updateAns()
            }
        }

        lvWrongAns.setOnItemClickListener() {
            parent, view, position, id ->
            rightView?.setBackgroundColor(Color.TRANSPARENT)
            if (!view.equals(rightView)) {
                view.setBackgroundColor(Color.GREEN)
                rightView = view
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            var thisUser = db.getUser(currentUsername)
            btnCreateBond.setOnClickListener {
                bondController.createBond(
                    quest,
                    ansList,
                    view?.id!!.toInt()
                )
            }
        }

    }

    private fun updateAns() {
        val lvWrongAns = requireView().findViewById<ListView>(R.id.lv_wrong_ans)
        lvWrongAns.adapter = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
//            arrayOf(ansList.values.toList())
            ansList
        )
    }

}