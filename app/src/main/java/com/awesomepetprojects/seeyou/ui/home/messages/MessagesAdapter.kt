package com.awesomepetprojects.seeyou.ui.home.messages

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.awesomepetprojects.seeyou.data.models.message.MessageViewType
import com.awesomepetprojects.seeyou.data.models.message.Message
import com.awesomepetprojects.seeyou.databinding.ItemMessageBinding
import com.awesomepetprojects.seeyou.databinding.ItemMessagePhotoBinding

class MessagesAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            MessageViewType.CONTENT.ordinal -> {
                val binding =
                    ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentViewHolder(binding)
            }

            MessageViewType.USER_PHOTO.ordinal -> {
                val binding = ItemMessagePhotoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                PhotoViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Wrong view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val currentMessage = getItem(position)) {
            is Message.Content -> (holder as ContentViewHolder).bind(currentMessage)
            is Message.UserPhoto -> (holder as PhotoViewHolder).bind(currentMessage)
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is Message.Content -> MessageViewType.CONTENT.ordinal
        is Message.UserPhoto -> MessageViewType.USER_PHOTO.ordinal
        else -> throw IllegalArgumentException("Wrong view type")
    }


    inner class ContentViewHolder(private val binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(content: Message.Content) {
            setGravity(content.posterUid)
            setContent(content)
        }

        private fun setGravity(posterUid: String) {
            binding.root.gravity = when (posterUid) {
                "s" -> Gravity.END
                else -> Gravity.START
            }
        }

        private fun setContent(content: Message.Content) {
            binding.apply {
                textMessage.text = content.message
                textCreateDate.text = content.createDate
            }
        }
    }

    inner class PhotoViewHolder(private val binding: ItemMessagePhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Message.UserPhoto) {
            setGravity(photo.posterUid)
        }

        private fun setGravity(posterUid: String) {
            binding.root.gravity = when (posterUid) {
                "s" -> Gravity.END
                else -> Gravity.START
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
                if (oldItem::class != newItem::class) {
                    return false
                }
                if (oldItem is Message.Content) {
                    return oldItem.id == (newItem as Message.Content).id
                }
                if (oldItem is Message.UserPhoto) {
                    return oldItem.id == (newItem as Message.UserPhoto).id
                }
                throw IllegalArgumentException("Wrong items")
            }

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                if (oldItem::class != newItem::class) {
                    return false
                }
                if (oldItem is Message.Content) {
                    return oldItem == newItem
                }
                if (oldItem is Message.UserPhoto) {
                    return oldItem == newItem
                }
                throw IllegalArgumentException("Wrong items")
            }
        }
    }
}