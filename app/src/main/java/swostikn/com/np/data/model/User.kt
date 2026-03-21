package swostikn.com.np.data.model

/**
 * Model representing a User in the system.
 */
data class User(
    val id: String,
    val email: String,
    val name: String? = null
)
