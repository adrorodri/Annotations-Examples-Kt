package com.adrorodri.annotationsexamples.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrorodri.annotationsexamples.R
import com.adrorodri.annotationsexamples.runtimeAnnotations.RuntimeProcessor
import com.adrorodri.annotationsexamples.runtimeAnnotations.RuntimeReflectionTestAnnotation
import kotlinx.android.synthetic.main.fragment_runtime_annotation.*

class RuntimeFragment : Fragment() {

    @RuntimeReflectionTestAnnotation(5)
    private var value: Int = 1000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_runtime_annotation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPreviousValue.text = String.format("before reflection processing %s", value.toString())
        RuntimeProcessor.bindReflectionValue(this)
        tvNewValue.text = String.format("after reflection processing %s", value.toString())
    }

    companion object {
        @JvmStatic
        fun newInstance() = RuntimeFragment()
    }
}
