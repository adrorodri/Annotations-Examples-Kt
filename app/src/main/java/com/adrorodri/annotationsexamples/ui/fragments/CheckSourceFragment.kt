package com.adrorodri.annotationsexamples.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adrorodri.annotation.CheckSourceTestAnnotation
import com.adrorodri.annotationsexamples.R

@CheckSourceTestAnnotation
class CheckSourceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_source, container, false)
    }

    companion object {

        // static fields in the class are set to be all caps mandatory
        // check build error when the field is not all caps

        const val TAG = "AnnotateTest"

        @JvmStatic
        fun newInstance() = CheckSourceFragment()
    }
}
