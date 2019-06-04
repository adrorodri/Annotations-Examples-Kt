package com.adrorodri.annotationsexamples.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adrorodri.annotation.GenerateSourceTestAnnotation
import com.adrorodri.annotationsexamples.R
import kotlinx.android.synthetic.main.fragment_generate_source.*

class GenerateSourceFragment : Fragment() {

    @GenerateSourceTestAnnotation(3)
    var test: Int = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generate_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Generated Method
        bindGenerationValue(this)
        tvGeneratedValue.text = test.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = GenerateSourceFragment()
    }
}
