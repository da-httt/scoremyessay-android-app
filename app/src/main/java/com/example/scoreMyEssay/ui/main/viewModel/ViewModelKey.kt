package com.example.scoreMyEssay.ui.main.viewModel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

annotation class ViewModelKey {
    @Target(AnnotationTarget.FUNCTION,
            AnnotationTarget.PROPERTY_GETTER,
            AnnotationTarget.PROPERTY_SETTER)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)
}