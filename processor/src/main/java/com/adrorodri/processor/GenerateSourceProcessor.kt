package com.adrorodri.processor

import com.adrorodri.annotation.CheckSourceTestAnnotation
import com.adrorodri.annotation.GenerateSourceTestAnnotation
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@AutoService(Processor::class)
class GenerateSourceProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    private val generatedSourcesRoot by lazy { processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME].orEmpty() }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(CheckSourceTestAnnotation::class.java.canonicalName)
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        if (generatedSourcesRoot.isEmpty()) {
            printError("Can't find the target directory for generated Kotlin files.")
            return false
        }

        roundEnv.getElementsAnnotatedWith(GenerateSourceTestAnnotation::class.java).forEach { fieldElement ->
            if (fieldElement.kind != ElementKind.FIELD) {
                printError("Can only be applied to field, element: $fieldElement")
                return false
            } else {
                prepareFieldInitialization(fieldElement)
            }
        }
        return false
    }

    private fun prepareFieldInitialization(fieldElement: Element) {
        val packageOfMethod = processingEnv.elementUtils.getPackageOf(fieldElement).toString()

        val annotatedValue = fieldElement.getAnnotation(GenerateSourceTestAnnotation::class.java).value

        val funcBuilder = FunSpec.builder("bindGenerationValue")
            .addModifiers(KModifier.PUBLIC)
            .addParameter("parent", fieldElement.enclosingElement.asType().asTypeName())
            .addStatement("parent.%L = %L", fieldElement.simpleName, annotatedValue)

        val file = File(generatedSourcesRoot)
        file.mkdir()
        FileSpec.builder(packageOfMethod, "GeneratedFunction").addFunction(funcBuilder.build()).build().writeTo(file)
    }

    private fun printError(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, message)
    }

    private fun printWarning(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, message)
    }
}