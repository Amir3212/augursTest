package com.inerviewapp.utils.useCases

class ValidatePassword {
    fun invoke(password: String): Result {
        return when {
            password.isEmpty() -> {
                Result(
                    success = false, error = "password is empty !!"
                )
            }

            else -> Result(
                success = true
            )
        }


    }

}