package com.akin.casestudy.navigation

enum class DeepLinks(val uri:String) {
    HOME_SCREEN_DEEPLINK("animeson://homeScreen"),
    DETAIL_SCREEN_DEEPLINK("animeson://detailScreen/{pokeId}"),
    MESSAGE_BOX_SCREEN_DEEPLINK("animeson://messageBoxScreen"),
    PROFILE_SCREEN_DEEPLINK("animeson://profileScreen"),
    SIGNUP_SCREEN_DEEPLINK("animeson://signUpScreen"),
   LOGIN_SCREEN_DEEPLINK("animeson://LoginScreen")
}