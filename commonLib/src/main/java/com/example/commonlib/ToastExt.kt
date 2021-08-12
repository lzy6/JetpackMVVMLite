package com.example.commonlib

import android.content.Context
import android.view.View
import com.example.commonlib.utils.UtilToast
import com.google.android.material.snackbar.Snackbar

/**
 * 吐司
 */
fun toast(toast: String) =
    UtilToast.showToast(CommonAPP.instance, toast)

/**
 * Snackbar吐司
 */
fun Context.snackBarToast(view: View, strId: Int) =
    Snackbar.make(view, getString(strId), Snackbar.LENGTH_SHORT).show()