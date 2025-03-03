import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testappforcourses.domain.model.User
import com.example.testappforcourses.domain.usecase.GetUsersUseCase
import com.example.testappforcourses.presentation.viewmodel.UserViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // Для тестирования LiveData

    private val testDispatcher = StandardTestDispatcher() // Тестовый диспетчер

    private lateinit var viewModel: UserViewModel
    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val observer: Observer<List<User>> = mockk(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Устанавливаем тестовый Main диспетчер

        every { getUsersUseCase.execute() } returns flowOf(mockUserList)

        viewModel = UserViewModel(getUsersUseCase)
        viewModel.users.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Сбрасываем тестовый диспетчер
    }

    @Test
    fun `fetchUsers should update LiveData with user list`() = runTest {
        advanceUntilIdle() // Ждём завершения всех корутин
        verify { observer.onChanged(mockUserList) }
    }

    companion object {
        private val mockUserList = listOf(
            User(1, "dddd rrrr", "eeee@test.com", "12223456", "New York"),
            User(2, "dddd ssssss", "rrrr@test.com", "65433321", "Moskow")
        )
    }
}