package com.adrorodri.annotationsexamples.runtimeAnnotations

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeReflectionTestAnnotation(val realValue: Int)