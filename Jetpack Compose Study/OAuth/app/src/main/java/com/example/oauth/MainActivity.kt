package com.example.oauth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.oauth.ui.theme.OAuthTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

val ID = "1031297475167-s6hc95cen5b8ag4fvnqkuqkk4l8o4va9.apps.googleusercontent.com"
class MainActivity : ComponentActivity() {
    private lateinit var GoogleSignResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ID)
            .requestServerAuthCode(ID)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account == null) {
            Log.e("Google account", "로그인 안되있음")
        } else {
            Log.e("Google account", "로그인 완료된 상태")
        }

        fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account = completedTask.getResult(ApiException::class.java)
                val email = account?.email.toString()
                var googletoken = account?.idToken.toString()
                var googletokenAuth = account?.serverAuthCode.toString()

                Log.e("Google account",email)
                Log.e("Google account",googletoken)
                Log.e("Google account", googletokenAuth)
            } catch (e: ApiException){
                Log.e("Google account","signInResult:failed Code = " + e.statusCode)
            }
        }

        GoogleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){ result ->
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
        setContent {
            OAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(
                        onClick = {
                            var signIntent: Intent = mGoogleSignInClient.getSignInIntent()
                            GoogleSignResultLauncher.launch(signIntent)
                        }
                    )
                }
            }
        }

    }
}



/*
class MainActivity : ComponentActivity() {
    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    //google client
    private lateinit var googleSignInClient: GoogleSignInClient

    //private const val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1031297475167-p9818vehrv5ke3kmaor2p8d93rekpjr7.apps.googleusercontent.com")
            //'R.string.default_web_client_id' 에는 본인의 클라이언트 아이디를 넣어주시면 됩니다.
            //저는 스트링을 따로 빼서 저렇게 사용했지만 스트링을 통째로 넣으셔도 됩니다.
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //firebase auth 객체
        firebaseAuth = FirebaseAuth.getInstance()
        setContent {
            OAuthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(
                        onClick = {
                            signIn()
                        }
                    )
                }
            }
        }
    }
    // onStart. 유저가 앱에 이미 구글 로그인을 했는지 확인
    public override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!==null){ // 이미 로그인 되어있을시 바로 메인 액티비티로 이동
            //toMainActivity(firebaseAuth.currentUser)
        }
    } //onStart End

    // onActivityResult
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("onActivityResult", "시작했서용요ㅛㅛㅇ")
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d("onActivityResult", "if문 시작했서용요ㅛㅛㅇ")
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LoginActivity", "Google sign in failed", e)
            }
            Log.d("onActivityResult", "if 끝났서요용ㅇㅛㅛㅇ")
        }
        Log.d("onActivityResult", "끝났서요용ㅇㅛㅛㅇ")
    } // onActivityResult End

    // firebaseAuthWithGoogle
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("LoginActivity", "firebaseAuthWithGoogle:" + acct.id!!)

        //Google SignInAccount 객체에서 ID 토큰을 가져와서 Firebase Auth로 교환하고 Firebase에 인증
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 성공", task.exception)
                    toMainActivity(firebaseAuth?.currentUser)
                } else {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 실패", task.exception)
                    //Snackbar.make(login_layout, "로그인에 실패하였습니다.", Snackbar.LENGTH_SHORT).show()
                }
            }
    }// firebaseAuthWithGoogle END


    // toMainActivity
    fun toMainActivity(user: FirebaseUser?) {
        if(user !=null) { // MainActivity 로 이동
            Log.d("SUCCESSSSSSSSS", "YEYEEEYEEEEYEEYEEEEYEEEYEEEYEEYEYEYEE")
            //startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    } // toMainActivity End

    // signIn
    private fun signIn() {
        Log.d("signIn", "시작했서용요ㅛㅛㅇ")
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        Log.d("signIn", "끝났서요용ㅇㅛㅛㅇ")
    }
    // signIn End


    private fun signOut() { // 로그아웃
        // Firebase sign out
        firebaseAuth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)
        }
    }

    private fun revokeAccess() { //회원탈퇴
        // Firebase sign out
        firebaseAuth.signOut()
        googleSignInClient.revokeAccess().addOnCompleteListener(this) {

        }
    }

}
 */

@Composable
fun Greeting(onClick: () -> Unit) {
    Button(onClick = {onClick()}) {
        Text(text = "구글 로그인")
    }
}