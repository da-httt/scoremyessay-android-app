package com.example.scoremyessay.data.model.user

import com.example.scoremyessay.data.model.user.avatar.UserAvatar
import com.example.scoremyessay.data.model.user.credit_card.CreditCardUser
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AccountData(
    val user_id: Int,
    val email: String,
    val name: String,
    val phone_number: String,
    val gender_id : Int,
    val job_id: Int,
    val date_of_birth: String,
    val address: String,

    var avatar: UserAvatar?,
    var creditCard: CreditCardUser?
): Serializable