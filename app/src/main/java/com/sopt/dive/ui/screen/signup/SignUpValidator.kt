package com.sopt.dive.ui.screen.signup

object SignUpValidator {
    private val ID_PATTERN = Regex("^[a-zA-Z0-9]{6,10}$")
    private val PW_PATTERN = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,12}$")
    private val NICK_PATTERN = Regex("^[a-zA-Z0-9]{1,100}$")
    private val EMAIL_PATTERN = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,150}$")
    private val AGE_PATTERN = Regex("^[1-9][0-9]{0,2}$")

    fun validateId(userId: String): String? =
        if (userId.isNotEmpty() && (!userId.matches(ID_PATTERN))) "6~10자의 영문 대소문자와 숫자만 가능합니다" else null

    fun validatePw(userPw: String): String? =
        if (userPw.isNotEmpty() && (!userPw.matches(PW_PATTERN))) "8~12자, 영문 대소문자·숫자·특수문자를 각각 1개 이상 포함해야합니다" else null

    fun validateNickname(userNickname: String): String? =
        if (userNickname.isNotEmpty() && (!userNickname.matches(NICK_PATTERN))) "1자 이상의 영문 대소문자와 숫자만 가능합니다" else null

    fun validateEmail(userEmail: String): String? =
        if (userEmail.isNotEmpty() && !userEmail.matches(EMAIL_PATTERN)) "유효한 이메일 형식이어야 합니다" else null

    fun validateAge(userAge: Int): String? =
        if (userAge == 0 && !userAge.toString().matches(AGE_PATTERN)) "숫자만 입력 가능합니다" else null

    fun validateAll(userId: String, userPw: String, userNickname: String, userEmail: String, userAge: Int): Boolean {
        return validateId(userId) == null
                && validatePw(userPw) == null
                && validateNickname(userNickname) == null
                && validateEmail(userEmail) == null
                && validateAge(userAge) == null
    }
}