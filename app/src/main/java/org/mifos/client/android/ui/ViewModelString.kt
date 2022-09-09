package org.mifos.client.android.ui

import android.content.Context
import androidx.annotation.StringRes

class ViewModelString(
    @StringRes private val stringResId: Int = 0,
    vararg stringResVarArgs: Int
) {
    private val stringResArgs: IntArray = stringResVarArgs


    fun resolve(context: Context): String {
        return when {
            stringResArgs.isNotEmpty() -> return context.getString(
                stringResId,
                *stringResArgs.map { context.getString(it) }.toTypedArray()
            )
            else -> context.getString(stringResId)
        }
    }
}