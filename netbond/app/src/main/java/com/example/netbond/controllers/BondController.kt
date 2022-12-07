package com.example.netbond.controllers

import com.example.netbond.models.Bond

class BondController {

    fun createBond(question: String, ansList: ArrayList<String>, idRight: Int) {
        var bond = Bond(question, ansList)

    }

}