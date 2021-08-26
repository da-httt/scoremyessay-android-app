package com.example.scoremyessay.data.model.user

import com.example.scoremyessay.data.model.user.avatar.UserAvatar
import com.example.scoremyessay.data.model.user.credit_card.CreditCardUser

data class AccountDataResponse(
    val account_id: Int,
    val disabled: Boolean,
    val email: String,
    val info: AccountData,
    val role_id: Int,

    var avatar: UserAvatar?,
    var creditCard: CreditCardUser?
)