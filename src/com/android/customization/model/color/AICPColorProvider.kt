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
import com.android.customization.pickeR.color.aicp_shared.model.ColorType
import com.android.systemui.monet.ColorScheme
import com.android.themepicker.R

class AICPColorProvider(private val context: Context) {
    fun getAICPColors(): List<ColorOptionImpl> {
        val colors = mutableListOf<ColorOptionImpl>()

        // Get AICP colors from resources
        val colorMap = listOf(
            Pair(R.color.aicp_amberlight, "Amber Light"),
            Pair(R.color.aicp_amberdark, "Amber Dark"),
            Pair(R.color.aicp_bluelight, "Blue Light"),
            Pair(R.color.aicp_carnationlight, "Carnation Light"),
            Pair(R.color.aicp_carnationdark, "Carnation Dark"),
            Pair(R.color.aicp_cyan, "Cyan"),
            Pair(R.color.aicp_denim, "Denim"),
            Pair(R.color.aicp_etherealblue, "Ethereal Blue"),
            Pair(R.color.aicp_etherealgreen, "Ethereal Green"),
            Pair(R.color.aicp_etherealpink, "Ethereal Pink"),
            Pair(R.color.aicp_gold, "Gold"),
            Pair(R.color.aicp_greenlight, "Green Light"),
            Pair(R.color.aicp_grey, "Grey"),
            Pair(R.color.aicp_hopelight, "Hope Light"),
            Pair(R.color.aicp_hopedark, "Hope Dark"),
            Pair(R.color.aicp_indigoaicp, "Indigo Aicp"),
            Pair(R.color.aicp_lavalight, "Lava Light"),
            Pair(R.color.aicp_lavadark, "Lava Dark"),
            Pair(R.color.aicp_lime, "Lime"),
            Pair(R.color.aicp_orange, "Orange"),
            Pair(R.color.aicp_oxygen, "Oxygen"),
            Pair(R.color.aicp_pinklight, "Pink Light"),
            Pair(R.color.aicp_pinkdark, "Pink Dark"),
            Pair(R.color.aicp_pixellight, "Pixel Light"),
            Pair(R.color.aicp_pixeldark, "Pixel Dark"),
            Pair(R.color.aicp_purple, "Purple"),
            Pair(R.color.aicp_red, "Red"),
            Pair(R.color.aicp_teallight, "Teal Light"),
            Pair(R.color.aicp_tealdark, "Teal Dark"),
            Pair(R.color.aicp_turquoiselight, "Turquoise Light"),
            Pair(R.color.aicp_turquoisedark, "Turquoise Dark"),
            Pair(R.color.aicp_yellowlight, "Yellow Light"),
            Pair(R.color.aicp_yellowdark, "Yellow Dark")
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
