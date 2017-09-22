package com.example.tinybooking.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tinybooking.R
import com.example.tinybooking.dao.UserData
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.fragment_sign_in.view.*

/**
 * Created by schecterza on 9/20/2017.
 */

class SignInFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleApiClient: GoogleApiClient
    lateinit var btnSignIn: SignInButton

    private val TAG = "SignInActivity"
    private val RC_SIGN_IN = 9001

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_sign_in, container, false)
        initInstances(rootView)
        return rootView
    }

    fun initInstances(rootView: View) {

        mAuth = FirebaseAuth.getInstance()

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = GoogleApiClient.Builder(context)
                .enableAutoManage(activity /* FragmentActivity */, GoogleApiClient.OnConnectionFailedListener { }/* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        btnSignIn = rootView.sign_in_button
        btnSignIn.setOnClickListener(myOnclick)


    }


    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
    }

    var myOnclick = View.OnClickListener { v ->
        when (v) {
            v -> signIn()
        }
    }

    private fun signIn() {
        var signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)
            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        Toast.makeText(activity, "Authentication successful.",
                                Toast.LENGTH_LONG).show()
                        val user = mAuth.currentUser
                        updateUser(user)
                        fragmentManager.popBackStack()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Toast.makeText(activity, "Authentication failed.",
                                Toast.LENGTH_LONG).show()
                        updateUser(null)
                    }

                    // ...
                })
    }

    private fun updateUser(user: FirebaseUser?) {

        if (user != null) {
            Toast.makeText(activity, user.displayName, Toast.LENGTH_LONG).show()
            UserData.setUserData(user)
        }

    }

    companion object {
        fun newInstance(): SignInFragment {
            var fragment = SignInFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}