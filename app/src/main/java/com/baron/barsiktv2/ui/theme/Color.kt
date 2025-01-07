package com.baron.barsiktv2.ui.theme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GrayBlack = Color(0xFF232323)
val GrayBlack2 = Color(0xFF3A3939)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Composable
fun getMainColors(): TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        disabledTextColor = Color.White,
        errorTextColor = Color.Unspecified,
        focusedContainerColor = Color.Unspecified,
        unfocusedContainerColor = Color.Unspecified,
        disabledContainerColor = Color.Unspecified,
        errorContainerColor = Color.Unspecified,
        cursorColor = Color.White,
        errorCursorColor = Color.Unspecified,
        selectionColors = null,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = Color.White,
        disabledBorderColor = Color.White,
        errorBorderColor = Color.Unspecified,
        focusedLeadingIconColor = Color.Unspecified,
        unfocusedLeadingIconColor = Color.Unspecified,
        disabledLeadingIconColor = Color.Unspecified,
        errorLeadingIconColor = Color.Unspecified,
        focusedTrailingIconColor = Color.Unspecified,
        unfocusedTrailingIconColor = Color.Unspecified,
        disabledTrailingIconColor = Color.Unspecified,
        errorTrailingIconColor = Color.Unspecified,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.White,
        disabledLabelColor = Color.White,
        errorLabelColor = Color.Unspecified,
        focusedPlaceholderColor = Color.Unspecified,
        unfocusedPlaceholderColor = Color.Unspecified,
        disabledPlaceholderColor = Color.Unspecified,
        errorPlaceholderColor = Color.Unspecified,
        focusedSupportingTextColor = Color.Unspecified,
        unfocusedSupportingTextColor = Color.Unspecified,
        disabledSupportingTextColor = Color.Unspecified,
        errorSupportingTextColor = Color.Unspecified,
        focusedPrefixColor = Color.Unspecified,
        unfocusedPrefixColor = Color.Unspecified,
        disabledPrefixColor = Color.Unspecified,
        errorPrefixColor = Color.Unspecified,
        focusedSuffixColor = Color.Unspecified,
        unfocusedSuffixColor = Color.Unspecified,
        disabledSuffixColor = Color.Unspecified,
        errorSuffixColor = Color.Unspecified,
    )
}