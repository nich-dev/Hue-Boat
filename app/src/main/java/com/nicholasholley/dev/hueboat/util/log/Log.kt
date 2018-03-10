package com.nicholasholley.dev.hueboat.util.log

import timber.log.Timber

/**
 * Static wrapper class for Timber.
 */
class Log {
    companion object {
        fun d(msg: String, vararg args: Any?){
            Timber.d(msg, args)
        }
        fun d(throwable: Throwable, msg: String?, vararg args: Any?){
            Timber.d(throwable, msg, args)
        }
        fun i(msg: String, vararg args: Any?){
            Timber.i(msg, args)
        }
        fun i(throwable: Throwable, msg: String?, vararg args: Any?){
            Timber.i(throwable, msg, args)
        }
        fun w(msg: String, vararg args: Any?){
            Timber.w(msg, args)
        }
        fun w(throwable: Throwable, msg: String?, vararg args: Any?){
            Timber.w(throwable, msg, args)
        }
        fun e(msg: String, vararg args: Any?){
            Timber.e(msg, args)
        }
        fun e(throwable: Throwable, msg: String?, vararg args: Any?){
            Timber.e(throwable, msg, args)
        }
    }
}