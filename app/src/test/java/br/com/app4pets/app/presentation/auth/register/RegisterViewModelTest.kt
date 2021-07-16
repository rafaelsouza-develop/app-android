package br.com.app4pets.app.presentation.auth.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.app4pets.app.base.ViewState
import br.com.app4pets.app.data.network.Result
import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.data.network.models.LoginResponse
import br.com.app4pets.app.data.network.models.RegisterRequest
import br.com.app4pets.app.data.network.models.RegisterResponse
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.domain.repository.AuthRepository
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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val contextProvider = TestCoroutineContextProvider()
    private val authRepository: AuthRepository = mockk()
    private val registerRequest: RegisterRequest = mockk()
    private val registerResponse: Result<RegisterResponse> = mockk()

    private val registerEventObserver: Observer<ViewState<RegisterResponse, ResponseStatus>> =
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
    fun `WHEN register to called THEN return a Response Success with User `() {
        coEvery { authRepository.register(registerRequest) } returns registerResponse

        instantiate().register(registerRequest)

        coVerify {
            authRepository.register(registerRequest)
        }
    }

    private fun instantiate() =
        RegisterViewModel(contextProvider, authRepository).apply {
            registerLiveData.observeForever(registerEventObserver)
        }
}