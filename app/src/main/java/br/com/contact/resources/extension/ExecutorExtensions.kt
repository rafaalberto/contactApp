package br.com.contact.resources.extension

import java.util.concurrent.Callable
import java.util.concurrent.Executors.newSingleThreadExecutor

fun <T> runAsync(block: () -> T): T =
    newSingleThreadExecutor().submit(Callable { block() }).get()

fun doInBackground(block: () -> Unit): Unit =
    newSingleThreadExecutor().submit { block() }.let { Unit }