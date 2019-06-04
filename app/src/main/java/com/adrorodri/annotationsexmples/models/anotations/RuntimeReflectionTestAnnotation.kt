package com.adrorodri.annotationsexmples.models.anotations

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeReflectionTestAnnotation(val realValue: Int)