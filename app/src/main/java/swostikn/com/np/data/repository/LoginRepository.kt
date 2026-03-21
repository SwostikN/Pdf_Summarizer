package swostikn.com.np.data.repository

import kotlinx.coroutines.delay
import swostikn.com.np.data.model.User

/**
 * Repository class that handles data operations for Login.
 * This abstracts the data source (Backend API, Local DB, etc.) from the ViewModel.
 */
class LoginRepository {

    /**
     * Simulates a backend call for authentication.
     */
    suspend fun login(email: String, password: String): Result<User> {
        return try {
            // Simulate network latency
            delay(2000)

            // Mock validation logic
            if (email.contains("@") && password.length >= 6) {
                Result.success(User(id = "123", email = email, name = "John Doe"))
            } else {
                Result.failure(Exception("Invalid email or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
