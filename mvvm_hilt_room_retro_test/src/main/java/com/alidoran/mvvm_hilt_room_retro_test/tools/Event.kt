package com.alidoran.mvvm_hilt_room_retro_test.tools

open class Event <out T>(private val content: T){
    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled():T?{
        return  if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }
    }
    fun peekContent():T = content
}