/*
 * SPDX-FileCopyrightText: DerpFest AOSP
 * SPDX-License-Identifier: Apache-2.0
 */

package com.android.customization.model.color

import android.content.Context
import android.content.theming.ThemeStyle
import android.graphics.Color
import com.android.customization.model.ResourceConstants
import com.android.customization.model.color.ColorOptionsProvider.COLOR_SOURCE_PRESET
import com.android.customization.picker.color.shared.model.ColorType
import com.android.systemui.monet.ColorScheme
import com.android.themepicker.R

class AICPColorProvider(private val context: Context) {
    fun getAICPColors(): List<ColorOptionImpl> {
        val colors = mutableListOf<ColorOptionImpl>()

        // Get AICP colors from resources
        val colorMap = listOf(
            Pair(R.color.amberlight, "Amber Light"),
            Pair(R.color.amberdark, "Amber Dark"),
            Pair(R.color.bluelight, "Blue Light"),
            Pair(R.color.carnationlight, "Carnation Light"),
            Pair(R.color.carnationdark, "Carnation Dark"),
            Pair(R.color.cyan, "Cyan"),
            Pair(R.color.denim, "Denim"),
            Pair(R.color.etherealblue, "Ethereal Blue"),
            Pair(R.color.etherealgreen, "Ethereal Green"),
            Pair(R.color.etherealpink, "Ethereal Pink"),
            Pair(R.color.gold, "Gold"),
            Pair(R.color.greenlight, "Green Light"),
            Pair(R.color.grey, "Grey"),
            Pair(R.color.hopelight, "Hope Light"),
            Pair(R.color.hopedark, "Hope Dark"),
            Pair(R.color.indigoaicp, "Indigo Aicp"),
            Pair(R.color.lavalight, "Lava Light"),
            Pair(R.color.lavadark, "Lava Dark"),
            Pair(R.color.lime, "Lime"),
            Pair(R.color.orange, "Orange"),
            Pair(R.color.oxygen, "Oxygen"),
            Pair(R.color.pinklight, "Pink Light"),
            Pair(R.color.pinkdark, "Pink Dark"),
            Pair(R.color.pixellight, "Pixel Light"),
            Pair(R.color.pixeldark, "Pixel Dark"),
            Pair(R.color.purple, "Purple"),
            Pair(R.color.red, "Red"),
            Pair(R.color.teallight, "Teal Light"),
            Pair(R.color.tealdark, "Teal Dark"),
            Pair(R.color.turquoiselight, "Turquoise Light"),
            Pair(R.color.turquoisedark, "Turquoise Dark"),
            Pair(R.color.yellowlight, "Yellow Light"),
            Pair(R.color.yellowdark, "Yellow Dark")
        )

        // Create color options for each AICP color
        colorMap.forEachIndexed { index, (colorRes, name) ->
            val color = context.resources.getColor(colorRes, context.theme)
            val builder = ColorOptionImpl.Builder()
            builder.title = name
            builder.seedColor = color
            builder.source = COLOR_SOURCE_PRESET
            builder.type = ColorType.COLOR
            builder.style = ThemeStyle.VIBRANT
            builder.index = index + 1

            // Set light and dark theme colors
            val lightColorScheme = ColorScheme(color, /* darkTheme= */ false, ThemeStyle.VIBRANT)
            val darkColorScheme = ColorScheme(color, /* darkTheme= */ true, ThemeStyle.VIBRANT)
            
            builder.lightColors = getLightColorPreview(lightColorScheme)
            builder.darkColors = getDarkColorPreview(darkColorScheme)

            // Add overlay packages
            builder.addOverlayPackage(ResourceConstants.OVERLAY_CATEGORY_COLOR, toColorString(color))
            builder.addOverlayPackage(ResourceConstants.OVERLAY_CATEGORY_SYSTEM_PALETTE, toColorString(color))

            colors.add(builder.build())
        }

        return colors
    }

    private fun getLightColorPreview(colorScheme: ColorScheme): IntArray {
        return intArrayOf(
            colorScheme.accentColor,
            colorScheme.accentColor,
            colorScheme.accentColor,
            colorScheme.accentColor
        )
    }

    private fun getDarkColorPreview(colorScheme: ColorScheme): IntArray {
        return intArrayOf(
            colorScheme.accentColor,
            colorScheme.accentColor,
            colorScheme.accentColor,
            colorScheme.accentColor
        )
    }

    private fun toColorString(color: Int): String {
        return String.format("#%08X", color)
    }
} 
