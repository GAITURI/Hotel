package com.example.hotel.utils

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


//Firebase Cloud Messaging token manager is essential for sending messages to specific app instances
// the logic for retrieving, refreshing and sending these tokens to my backend server can be scattered if not managed properly
//to manage these messages an FCMTOKENMANAGER, centralizes all these operations in one place
//services such as getting the token when app starts or when a user logs in



object FcmTokenManager {

    private const val TAG= "FcmTokenManager"
    //first retrieve the current fcm registration token and send it to the backend server


    fun retrieveAndSendToken(){
       CoroutineScope(Dispatchers.IO).launch{
           try{
               val token= FirebaseMessaging.getInstance().token.await()
               if(token!=null){
                   Log.d(TAG, "FCM Registration Token: $token")
                    sendTokenToServer(token)

               }else{
                   Log.w(TAG, "FCM TOKEN is null after retrieval")
               }

           }catch (e:Exception){
               Log.e(TAG, "Error retrieving FCM registration token", e)

           }
       }
   }

    fun handleNewToken(token:String){
        Log.d(TAG, "New FCM Registration Token: $token")
        CoroutineScope(Dispatchers.IO).launch{
            sendTokenToServer(token)
        }
    }


    private fun sendTokenToServer(token: String) {
            val userId= getCurrentUserId()
        if(userId == null){
            Log.w(TAG, "User  ID is null. Cannot send FCM TOKEN to server without  a logged in user")
            return
        }
        Log.i(TAG,"Sending FCM token to server for user $userId")

    }
    private fun getCurrentUserId():String?{
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return firebaseUser?.uid
    }

}

//how FCM works
//client logs in firebase auth giving a userId
//the app gets FCM token
//the app stores the user id and fcmtoken in a firebase database like cloud firestore or realtime database
//once order is successfully processed , the cloud function code is executed, it reads the orderdetails, it determines the user id of the user who placed the order
//it queries your firebase databasee to retrieve the fcmtoken associated with that userid  and uses the firebase admin sdk availanle to construct and send the fcm message to the retrieved token