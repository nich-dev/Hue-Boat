package com.nicholasholley.dev.hueboat.di.modules

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/***
 * Model map for the view models to be able to be referenced.
 */

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelMap(
    val value: KClass<out ViewModel>
)