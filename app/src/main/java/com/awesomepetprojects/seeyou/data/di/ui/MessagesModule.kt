package com.awesomepetprojects.seeyou.data.di.ui

import com.awesomepetprojects.seeyou.ui.home.messages.MessagesViewModel
import org.koin.dsl.module

val messagesModule = module {

    single<MessagesViewModel> {
        MessagesViewModel()
    }
}