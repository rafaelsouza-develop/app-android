package br.com.app4pets.app.modules.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.repository.auth.AuthRepository
import br.com.app4pets.app.testeutils.TestCoroutineContextProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val contextProvider = TestCoroutineContextProvider()
    private val authRepository: AuthRepository = mockk()
    private val loginRequest: LoginRequest = mockk()
    private val loginResponse: Result<LoginResponse> = mockk()
    private val loginEventObserver: Observer<ViewState<LoginResponse, ResponseStatus>> =
        mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `WHEN callLogin is called THEN it should call login`() {

        coEvery { authRepository.login(loginRequest) } returns loginResponse
        instantiate().login(loginRequest)

        coVerify {
            loginEventObserver.onChanged(ViewState(null, ResponseStatus.SUCCESS))
            //authRepository.login(loginRequest)
            

        }
    }


    private fun instantiate(): LoginViewModel {
        val viewModel = LoginViewModel(contextProvider, authRepository).apply {
            loginLiveData.observeForever(loginEventObserver)
        }
        return viewModel
    }
}