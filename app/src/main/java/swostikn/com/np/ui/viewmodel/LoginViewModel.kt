package swostikn.com.np.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import swostikn.com.np.data.model.User
import swostikn.com.np.data.repository.LoginRepository

/**
 * ViewModel for the Login screen.
 * It communicates with the Repository and exposes State to the View via LiveData.
 */
class LoginViewModel(private val repository: LoginRepository = LoginRepository()) : ViewModel() {

    // Internal mutable state
    private val _loginResult = MutableLiveData<Result<User>?>()
    // External immutable LiveData for the View to observe
    val loginResult: LiveData<Result<User>?> = _loginResult

    // Loading state to show/hide progress indicators
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Trigger the login process.
     * This follows the flow: View -> ViewModel -> Repository -> Backend
     */
    fun login(email: String, password: String) {
        _isLoading.value = true
        
        // Launching in viewModelScope ensures the request is cancelled if ViewModel is cleared
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginResult.postValue(result)
            _isLoading.value = false
        }
    }

    /**
     * Reset the result state after navigation or error handling.
     */
    fun clearResult() {
        _loginResult.value = null
    }
}
