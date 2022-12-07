package com.example.netbond.models

data class Bond(
    var username: String? = null,
    var question: String? = null,
    var ans: HashMap<Int, String>
)
