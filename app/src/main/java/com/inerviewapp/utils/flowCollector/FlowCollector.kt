package com.inerviewapp.utils.flowCollector

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow


fun <T> Fragment.collectFlow(flow: Flow<T>, collect: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect(collect)
        }
    }
}


fun <T> AppCompatActivity.collectFlow(flow: Flow<T>, collect: (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        flow.collect(collect)
    }
}
