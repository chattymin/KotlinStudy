package com.example.oauth2


import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.oauth2.ui.theme.OAuth2Theme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

val ID = "18471436335-fem40599lrcepmuea7m4qaiioe5jrpln.apps.googleusercontent.com"
val DEBUG_ID = "18471436335-ci5rtfp1lck4av4hoh0k34rdb6jomdbj.apps.googleusercontent.com"
val RELEASE_ID = "18471436335-04fp9977bkk4csmvcgs7aj47lcpt6biq.apps.googleusercontent.com"

class MainActivity : ComponentActivity() {
    private lateinit var GoogleSignResultLauncher: ActivityResultLauncher<Intent>
    var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ID)
            .requestServerAuthCode(ID)
            .requestProfile()
            .requestEmail()
            .requestId()
            .build()
        // BE에 넘겨야할 정보를 여기서 추출하면 됨.

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        var account = GoogleSignIn.getLastSignedInAccount(this)

        if (account == null) {
            Log.e("Google account", "로그인 안되있음")
        } else {
            Log.e("Google account", "로그인 완료된 상태")
        }


        fun firebaseAuthWithGoogle(accountt : GoogleSignInAccount?){
            var credntial = GoogleAuthProvider.getCredential(accountt?.idToken, null)
            auth?.signInWithCredential(credntial)
                ?.addOnCompleteListener {task ->
                    if (task.isSuccessful) Log.d("Firebase Success", "네 성공했습니다.")
                    else Log.e("Firebase ERROR", "먼가 먼가 잘못됨")
                }
        }

        fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
            try {
                val account = completedTask.getResult(ApiException::class.java)
                val email = account?.email.toString()
                val name = account?.displayName.toString()
                val profileImg = account?.photoUrl
                var userId = account?.id.toString()

                var googletoken = account?.idToken.toString()
                var googletokenAuth = account?.serverAuthCode.toString()

                firebaseAuthWithGoogle(account)

                Log.e("Google account email", email)
                Log.e("Google account firstName", name)
                Log.e("Google account profileImg", "$profileImg")
                Log.e("Google account userId", userId)
                // 위에 정보 4개 BE로 넘기면 됨. 구글 로그인 버튼 누를때 마다 넘기기
            } catch (e: ApiException) {
                Log.e("Google account", "signInResult:failed Code = " + e.statusCode)
            }
        }

        GoogleSignResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }


        fun googleLogin(){
            var signIntent: Intent = mGoogleSignInClient.getSignInIntent()
            GoogleSignResultLauncher.launch(signIntent)
            //checkLogin()
        }


        fun onLogout() {
            mGoogleSignInClient.signOut().addOnCompleteListener {  }
            Log.d("LogOut", "로그아웃하셨습니다.")
        }

        fun revokeAccess() {
            mGoogleSignInClient.revokeAccess().addOnCompleteListener {  }
        }

        setContent {
            OAuth2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(
                        onClick = {
                            googleLogin()
                        },
                        logoutClick = {
                            onLogout()
                        },
                        revokeClick = {
                            revokeAccess()
                        }
                    )
                }
            }
        }

    }

}

@Composable
fun Greeting(onClick: () -> Unit, logoutClick: () -> Unit, revokeClick: () -> Unit) {
    Column(){
        Button(onClick = { onClick() }) {
            Text(text = "구글 로그인")
        }
        Button(onClick = { logoutClick() }) {
            Text(text = "구글 로그아웃")
        }
        Button(onClick = { revokeClick() }) {
            Text(text = "회원 탈퇴")
        }
    }
}