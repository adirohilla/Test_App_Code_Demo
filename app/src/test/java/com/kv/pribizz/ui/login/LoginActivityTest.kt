package com.kv.pribizz.ui.login

import android.content.Intent
import android.os.Build
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kv.pribizz.MyApplication
import com.kv.pribizz.R
import com.kv.pribizz.ui.login.LoginActivity
import com.kv.pribizz.ui.main.MainActivity
import com.kv.pribizz.ui.signup.SignupActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private lateinit var scenario: ActivityScenario<LoginActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun testActivityLaunch() {
        scenario.onActivity {
            assertEquals(LoginActivity::class.java.name, it.javaClass.name)
        }
    }

    @Test
    fun testClickSignup() {
        scenario.onActivity {
            val signupButton = it.findViewById<Button>(R.id.tv_signup_text)
            signupButton.performClick()

            val expectedIntent = Intent(it, SignupActivity::class.java)
            val actualIntent = getAndAssertNextStartedActivity(it)
            assertEquals(expectedIntent.component, actualIntent.component)
        }
    }

    @Test
    fun testLoginSuccess() {
        // Mock a successful login scenario

        // Verify that the MainActivity is started
        scenario.onActivity {
            // Check that the MainActivity is started after a successful login
            val expectedIntent = Intent(it, MainActivity::class.java)
            val actualIntent = getAndAssertNextStartedActivity(it)
            assertEquals(expectedIntent.component, actualIntent.component)
        }
    }
    private fun getAndAssertNextStartedActivity(activity: LoginActivity): Intent {
        var capturedIntent: Intent? = null

        // Launch the activity and observe it
        val scenario = ActivityScenario.launch(activity::class.java)

        // Use onActivity to interact with the launched activity
        scenario.onActivity { launchedActivity ->

            capturedIntent = getAndAssertNextStartedActivity(launchedActivity)
        }

        // Close the scenario to finish the activity
        scenario.close()

        // Return the captured intent for further assertions
        return capturedIntent ?: throw AssertionError("No activity was started")
    }
}
