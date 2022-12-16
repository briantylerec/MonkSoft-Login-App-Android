package com.monksoft.monklogin.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.monksoft.monklogin.R

class DialogFragment(private val txtTitle : String, private val txtSubTitle : String, val textButton : String ) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dialog, container, false)

        val title = view?.findViewById<TextView>(R.id.tvTitle)
        val subTitle = view?.findViewById<TextView>(R.id.tvSubTitle)
        val btnConfirm = view?.findViewById<TextView>(R.id.btnConfirm)

        title?.text = txtTitle.toString()
        subTitle?.text = txtSubTitle.toString()
        btnConfirm?.text = textButton.toString()

        btnConfirm?.setOnClickListener {
            dismiss()
        }
        return view
    }
}