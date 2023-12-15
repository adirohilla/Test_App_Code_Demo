package com.kv.pribizz.ui.login

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kv.pribizz.data.Repository
import com.kv.pribizz.datastoremanager.DataStoreManager
import com.kv.pribizz.model.CurrentPlanModel
import com.kv.pribizz.model.UserModel
import com.kv.pribizz.model.response.LoginResponseModel
import com.kv.pribizz.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertTrue

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @Mock
    lateinit var dataStoreManager: DataStoreManager

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testOnLoginClick_ValidationError() {
        loginViewModel.mobile.value = ""
        loginViewModel.password.value = ""

        loginViewModel.onLoginClick(mock(View::class.java))

        val result = loginViewModel.response.value
        assertTrue(result is NetworkResult.Error)
        assertEquals("Please enter mobile number", (result as NetworkResult.Error).message)
    }

    @Test
    fun testOnLoginClick_Success()= runBlocking {
        loginViewModel.mobile.value = "1234567890"
        loginViewModel.password.value = "password"

        // Mock the repository response
        val userModel = UserModel("12", "john", anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(),
            anyString(),
            anyString(),
            anyString(),
            ArrayList<CurrentPlanModel>())

        val successResponse = NetworkResult.Success(LoginResponseModel(userModel))
        `when`(repository.login(anyString(), anyString(), anyString()))
            .thenReturn(flowOf(successResponse))

        // Mock the data store manager
        doNothing().`when`(dataStoreManager).saveUser(userModel)

        loginViewModel.onLoginClick(mock(View::class.java))

        val result = loginViewModel.response.value
        assertTrue(result is NetworkResult.Success)
        assertEquals(userModel, (result as NetworkResult.Success).data)


        verify(loginViewModel).userLogin(userModel)
    }


}
