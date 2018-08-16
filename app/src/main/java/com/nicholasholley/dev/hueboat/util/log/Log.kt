package com.nicholasholley.dev.hueboat.util.log

import timber.log.Timber

/**
 * Static wrapper class for Timber.
 */
class Log {
    companion object {
        fun d(msg: String, vararg args: Any?){
            try {
                Timber.d(msg, args)
            } catch (e: Exception) {
            }
        }
        fun d(throwable: Throwable, msg: String?, vararg args: Any?){
            try {
                Timber.d(throwable, msg, args)
            } catch (e: Exception) {
            }
        }
        fun i(msg: String, vararg args: Any?){
            try {
                Timber.i(msg, args)
            } catch (e: Exception) {
            }
        }
        fun i(throwable: Throwable, msg: String?, vararg args: Any?){
            try {
                Timber.i(throwable, msg, args)
            } catch (e: Exception) {
            }
        }
        fun w(msg: String, vararg args: Any?){
            try {
                Timber.w(msg, args)
            } catch (e: Exception) {
            }
        }
        fun w(throwable: Throwable, msg: String?, vararg args: Any?){
            try {
                Timber.w(throwable, msg, args)
            } catch (e: Exception) {
            }
        }
        fun e(msg: String, vararg args: Any?){
            try {
                Timber.e(msg, args)
            } catch (e: Exception) {
            }
        }
        fun e(throwable: Throwable, msg: String?, vararg args: Any?){
            try {
                Timber.e(throwable, msg, args)
            } catch (e: Exception) {
            }
        }
    }
}