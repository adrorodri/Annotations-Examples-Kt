package com.adrorodri.annotationsexmples.models.annotationsProcessors

import com.adrorodri.annotationsexmples.models.anotations.RuntimeReflectionTestAnnotation

class RuntimeProcessor {
    companion object {
        fun bindReflectionValue(target: Any) {
            val declaredFields = target::class.java.declaredFields

            for (field in declaredFields) {
                for (annotation in field.annotations) {
                    when (annotation) {
                        is RuntimeReflectionTestAnnotation -> {
                            field.isAccessible = true
                            field.set(target, annotation.realValue)
                        }
                    }
                }
            }
        }
    }
}