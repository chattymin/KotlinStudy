package com.example.okhttpex

object Constants {
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE{
    PHOTO,
    USER
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}
object API{
    const val BASE_URL = "https://api.unsplash.com/"

    const val CLIENT_ID: String = "NvJFaLri2bKx04GHa_31R-7LqELd32s3SLfOVxRrY4A"
    const val ACCESS_TOKEN: String = "testAccessToken"

    const val SEARCH_PHOTO = "search/photos"
    const val SEARCH_USERS = "search/users"
}