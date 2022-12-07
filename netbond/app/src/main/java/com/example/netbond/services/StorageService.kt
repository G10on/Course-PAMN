package com.example.netbond.services

import com.example.netbond.models.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class StorageService {

    private val db = FirebaseFirestore.getInstance()
    private val collUsers = db.collection("users")
    private val collBonds = db.collection("bonds")
    // private val auth = Firebase.auth

    suspend fun getUser(username: String): User? = collUsers
            .whereEqualTo("username", username)
            .get()
            .await()
            .single()
            .toObject<User>()

    fun updateUser(user : User?) : Boolean {

        val currentUsername = user!!.username!!

        collUsers
            .document(currentUsername)
            .update(mapOf(
                // Add remaining fields / Pass Custom class?
                "name" to user!!.name,
                "username" to user!!.username,
                "n_followings" to user!!.n_followings!!,
                "n_followers" to user!!.n_followers!!,
                "n_points" to user!!.n_points!!)
            )
        // ¿Volver a guardar resto de campos para generalizar a nuevo usuario?
        return true
    }

    suspend fun isThisRequestingToFollowExt(thisUser: User?, extUser: User?): Boolean {
        return collUsers
            .document(thisUser!!.username!!)
            .collection("sentRequests")
            .document(extUser!!.username!!).get().await().exists()
    }

    fun addNewRequest(thisUser: User?, extUser: User?, isFromThisUser:Boolean) {
        var coll = "receivedRequests"
        if (isFromThisUser) {
            coll = "sentRequests"
        }
        if (thisUser != null && extUser != null) {
            collUsers
                .document(thisUser.username.toString())
                .collection(coll)
                .document(extUser.username.toString())
                .set(hashMapOf<String, Any>("delete" to "this")) // update("timestamp", FieldValue.serverTimestamp())
                .addOnFailureListener{ e -> println(e)
                }
        }
    }

    fun removeRequest(thisUser: User?, extUser: User?, isFromThisUser:Boolean) {
        var coll = "receivedRequests"
        if (isFromThisUser) {
            coll = "sentRequests"
        }
        collUsers
            .document(thisUser!!.username!!)
            .collection(coll)
            .document(extUser!!.username!!)
            .delete()
    }

    suspend fun isThisFollowingExt(thisUser: User?, extUser: User?): Boolean {
        return collUsers
            .document(thisUser!!.username!!)
            .collection("followings")
            .document(extUser!!.username!!).get().await().exists()
    }

    fun removeFollow(thisUser: User?, extUser: User?, isFromThisUser: Boolean) {
        val coll = "followers"
        if (isFromThisUser) {
            val coll = "followings"
        }
        collUsers
            .document(thisUser!!.username!!)
            .collection(coll)
            .document(extUser!!.username!!).delete()
    }

}


















