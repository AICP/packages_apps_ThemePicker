package com.android.customization.picker.color.ui.binder

import android.view.View
import com.android.customization.picker.color.ui.viewmodel.ColorPickerViewModel
import net.margaritov.preference.colorpicker.ColorPickerDialog

object CustomColorSectionViewBinder {
    fun bind(
        view: View,
        viewModel: ColorPickerViewModel,
    ) {
        view.setOnClickListener {
            val currentColor = view.context.getColor(android.R.color.system_accent1_500)
            val dialog = ColorPickerDialog(view.context, currentColor)
            dialog.setAlphaSliderEnabled(false)
            dialog.setOnColorChangedListener { color ->
                viewModel.interactor.applyCustomColor(view.context, color)
            }
            dialog.show()
        }
    }
}
