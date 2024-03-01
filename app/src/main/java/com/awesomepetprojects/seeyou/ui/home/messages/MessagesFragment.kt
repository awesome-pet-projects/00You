package com.awesomepetprojects.seeyou.ui.home.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.awesomepetprojects.seeyou.databinding.FragmentMessagesBinding
import org.koin.android.ext.android.inject

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<MessagesViewModel>()

    private val userId by lazy {
        arguments?.getLong(USER_ID_PARAM_KEY, 0)
    }

    private lateinit var messagesAdapter: MessagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclers(requireActivity())
        setContent()
    }

    private fun setContent() {
        messagesAdapter.submitList(viewModel.getMessages())
    }

    private fun setupRecyclers(activity: FragmentActivity) {
        initAdapters()

        binding.apply {
            recyclerMessages.apply {
                adapter = messagesAdapter
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
            }
        }
    }

    private fun initAdapters() {
        messagesAdapter = MessagesAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val USER_ID_PARAM_KEY = "user_id"

        @JvmStatic
        fun newInstance(userId: Long) = MessagesFragment().apply {
            arguments = Bundle().apply {
                putLong(USER_ID_PARAM_KEY, userId)
            }
        }
    }
}