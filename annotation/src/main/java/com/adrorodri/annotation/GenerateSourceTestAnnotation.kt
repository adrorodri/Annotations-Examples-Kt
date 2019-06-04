package com.adrorodri.annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class GenerateSourceTestAnnotation(val value: Int)