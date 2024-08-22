package com.inerviewapp.navigation

import android.content.Intent
import androidx.fragment.app.Fragment
import com.inerviewapp.ui.HomeActivity

fun Fragment.navigateToHomeScreen() {
    startActivity(Intent(requireActivity(), HomeActivity::class.java))
    requireActivity().finish()
}
