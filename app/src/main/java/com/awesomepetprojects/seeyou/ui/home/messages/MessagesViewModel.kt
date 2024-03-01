package com.awesomepetprojects.seeyou.ui.home.messages

import androidx.lifecycle.ViewModel
import com.awesomepetprojects.seeyou.data.models.message.Message

class MessagesViewModel : ViewModel() {

    private val messages = mutableListOf<Message>(
        Message.Content(0, "s", "ss", "sss"),
        Message.Content(1, "s", "ss", "sss"),
        Message.Content(2, "q", "ss", "sss"),
        Message.Content(3, "s", "ss", "sss"),
        Message.Content(4, "q", "ss", "sss"),
        Message.Content(5, "s", "ss", "sss"),
        Message.Content(6, "s", "ss", "sss"),
        Message.Content(7, "q", "ss", "sss"),
        Message.Content(8, "s", "ss", "sss"),
        Message.Content(9, "q", "ss", "sss"),
    )

    fun getMessages(): List<Message> {
        val photosDetails = getPhotosDetails(messages)
        return getMessagesWithInsertedPhotos(messages, photosDetails)
    }

    private fun getMessagesWithInsertedPhotos(
        messages: MutableList<Message>, photosDetails: Map<Int, Message.UserPhoto>
    ): MutableList<Message> {
        val updatedMessages = messages

        for ((index, messagePhoto) in photosDetails) {
            updatedMessages.add(index, messagePhoto)
        }

        return updatedMessages
    }

    private fun getPhotosDetails(
        messages: MutableList<Message>
    ): Map<Int, Message.UserPhoto> {
        val photosDetailsMap = mutableMapOf<Int, Message.UserPhoto>()
        var tempUid: String? = null
        var positionIncrement = 0

        for ((index, message) in messages.withIndex()) {
            if (message is Message.Content || tempUid.isNullOrEmpty()) {
                if (tempUid != (message as Message.Content).posterUid) {
                    tempUid = message.posterUid

                    val messageUserPhoto = Message.UserPhoto(
                        id = message.id, posterUid = tempUid
                    )
                    photosDetailsMap[index + positionIncrement++] = messageUserPhoto
                }
            }
        }

        return photosDetailsMap
    }
}