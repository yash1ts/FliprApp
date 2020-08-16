package com.app.fliprapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.popup.view.*

class PopUP: DialogFragment() {
    val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.popup,container,false)
        getDialog()?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mainActivityViewModel.getDatabase()
        mainActivityViewModel.dataModel.observe(viewLifecycleOwner, Observer {
        view.pop_up_data.text = it.open
            view.prev_close.text = it.close
        })
        return view
    }
}