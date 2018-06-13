package com.danesfeder.kotlinapplication.command

interface Command<out T> {
    fun execute(): T
}