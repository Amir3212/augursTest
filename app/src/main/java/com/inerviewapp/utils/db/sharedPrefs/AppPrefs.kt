package com.inerviewapp.utils.db.sharedPrefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject


class AppPrefs @Inject constructor(
    application: Application,
) {

    private var sharedPreferences: SharedPreferences =
        application.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    var token: String?
        get() {
            return sharedPreferences.getString("token", null)
        }
        set(value) {
            editor.putString("token", value)
            commit()
        }


    private fun commit() {
        editor.commit()
    }


}