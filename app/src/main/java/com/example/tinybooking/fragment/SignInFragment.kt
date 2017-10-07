package com.example.tinybooking.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tinybooking.R
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.content.Intent
import android.widget.Toast
import android.util.Log
import com.chibatching.kotpref.Kotpref
import com.chibatching.kotpref.bulk
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.common.ConnectionResult

/**
 * Created by schecterza on 30/9/2017 AD.
 */

class SignInFragment: Fragment(), GoogleApiClient.OnConnectionFailedListener {

    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001

    // [START declare_auth]
    private var mAuth: FirebaseAuth? = null
    // [END declare_auth]

    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_sign_in, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        Kotpref.init(context)

        var signInBtn = rootView.sign_in_button
        signInBtn.setOnClickListener(myOnclick)


        // [START config_signin]
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        // [END config_signin]

        mGoogleApiClient = GoogleApiClient.Builder(context)
                .enableAutoManage(activity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]


    }

    var myOnclick = View.OnClickListener{v ->
        when(v.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    // [START on_start_check_user]
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.getCurrentUser()
        updateUI(currentUser)
    }

    // [END on_start_check_user]

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
        // [START_EXCLUDE silent]

        // [END_EXCLUDE]

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener( { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth!!.getCurrentUser()
                        updateUI(user)
                        activity.finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // [START_EXCLUDE]
                    // [END_EXCLUDE]
                })
    }
    // [END auth_with_google]

    // [START signin]
    private fun signIn() {
        Toast.makeText(context, "Signing In...", Toast.LENGTH_LONG).show()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signin]

    fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient)
    }

    private fun revokeAccess() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient)
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            var userInfo = com.example.tinybooking.dao.UserInfo
            userInfo.bulk {
                userTinyId = 123456
                userDisplayName = user!!.displayName!!
                userEmail = user.email!!
                userId = user.uid
                userPhotoUrl = user.photoUrl!!.toString()

            }
        }
        

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult)
        Toast.makeText(context, "Google Play Services error.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): SignInFragment {
            var fragment = SignInFragment()
            var args = Bundle()
            fragment.arguments = args

            return fragment
        }

        fun getInstance(): SignInFragment {
            var instance = SignInFragment()
            return instance
        }
    }


}