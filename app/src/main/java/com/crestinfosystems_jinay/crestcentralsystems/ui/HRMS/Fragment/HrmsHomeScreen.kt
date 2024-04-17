package com.crestinfosystems_jinay.crestcentralsystems.ui.HRMS.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crestinfosystems_jinay.crestcentralsystems.R



class HrmsHomeScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_hrms_home_screen, container, false)
    }

}