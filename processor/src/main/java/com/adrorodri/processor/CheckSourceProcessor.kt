package com.adrorodri.processor

import com.adrorodri.annotation.CheckSourceTestAnnotation
import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class CheckSourceProcessor : AbstractProcessor() {
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(CheckSourceTestAnnotation::class.java.canonicalName)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(CheckSourceTestAnnotation::class.java).forEach { classElement ->
            if (classElement.kind != ElementKind.CLASS) {
                printError("Can only be applied to field, element: $classElement")
                return false
            } else {
                checkAllCaps(classElement as TypeElement)
            }
        }
        return false
    }

    private fun checkAllCaps(classElement: TypeElement) {
        classElement.enclosedElements
            .filter {
                it.modifiers.contains(Modifier.STATIC)
            }.filter {
                it.kind == ElementKind.FIELD && it.simpleName.toString() != "Companion"
            }.filter {
                !it.simpleName.toString().isDefinedAllCaps()
            }.forEach {
                printError("Detected non-all-caps static field name: ${it.simpleName}.")
            }
    }

    private fun String.isDefinedAllCaps(): Boolean {
        val toCharArray = toCharArray()
        return toCharArray.none { it.isLowerCase() }
    }

    private fun printError(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
    }

    private fun printWarning(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, message)
    }
}