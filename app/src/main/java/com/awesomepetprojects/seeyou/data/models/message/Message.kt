package com.awesomepetprojects.seeyou.data.models.message

import android.net.Uri

sealed class Message {

    data class Content(
        val id: Long,
        val posterUid: String,
        val message: String,
        val createDate: String,
    ) : Message()

    data class UserPhoto(
        val id: Long, val posterUid: String, val photoUri: Uri? = null
    ) : Message()
}
