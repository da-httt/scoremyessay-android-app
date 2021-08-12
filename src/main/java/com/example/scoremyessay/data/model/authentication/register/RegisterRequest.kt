package com.example.scoremyessay.data.model.authentication.register

data class RegisterRequest(
    val address: String,
    val date_of_birth: String,
    val email: String,
    val gender_id: Int,
    val job_id: Int,
    val name: String,
    val password: String,
    val phone_number: String
)