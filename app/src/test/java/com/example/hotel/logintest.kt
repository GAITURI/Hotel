package com.example.hotel

import activities.LoginActivity
import activities.SignupActivity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import com.google.firebase.auth.FirebaseAuth
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog
import org.robolectric.shadows.ShadowNetworkInfo
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(sdk=[33])
class logintest {
    private lateinit var authMock: FirebaseAuth

    @Before
    fun setup(){
//        mock firebase statically to prevent "app not initialized" errors
            mockkStatic(FirebaseAuth::class)
            authMock= mockk(relaxed=true)
            every { FirebaseAuth.getInstance() } returns authMock

    // Mock ConnectivityManager for network tests
    val connectivityManager = RuntimeEnvironment.getApplication()
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val shadowConnectivityManager = shadowOf(connectivityManager)
    val networkInfo = ShadowNetworkInfo.newInstance(
        NetworkInfo.DetailedState.CONNECTED,
        ConnectivityManager.TYPE_WIFI, 0, true, true
    )
    shadowConnectivityManager.setActiveNetworkInfo(networkInfo)
}

@Test
fun `test login validation - empty fields`() {
    ActivityScenario.launch(LoginActivity::class.java).onActivity { activity ->
        val loginBtn = activity.findViewById<Button>(R.id.LoginButton)

        loginBtn.performClick()

        // Assert Toast message
        assertEquals("Fill all fields", ShadowToast.getTextOfLatestToast())
    }
}

@Test
fun `test navigation from login to signup`() {
    ActivityScenario.launch(LoginActivity::class.java).onActivity { activity ->
        val signUpText = activity.findViewById<TextView>(R.id.txtSignUp)

        signUpText.performClick()

        // Verify the next activity started
        val expectedIntent = Intent(activity, SignupActivity::class.java)
        val actualIntent = shadowOf(activity).nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }
}

@Test
fun `test forgot password dialog visibility`() {
    ActivityScenario.launch(LoginActivity::class.java).onActivity { activity ->
        activity.findViewById<TextView>(R.id.forgotPass).performClick()

        val dialog = ShadowAlertDialog.getLatestAlertDialog()
        assertNotNull(dialog)

        val shadowDialog = shadowOf(dialog)
        assertEquals("Reset Password", shadowDialog.title)
    }
}

@Test
fun `test signup password mismatch`() {
    ActivityScenario.launch(SignupActivity::class.java).onActivity { activity ->
        activity.findViewById<EditText>(R.id.etemail).setText("test@test.com")
        activity.findViewById<EditText>(R.id.etPassword).setText("123456")
        activity.findViewById<EditText>(R.id.confPassword).setText("654321")

        activity.findViewById<Button>(R.id.SignUpButton).performClick()

        assertEquals("Password does not match", ShadowToast.getTextOfLatestToast())
    }
}

}