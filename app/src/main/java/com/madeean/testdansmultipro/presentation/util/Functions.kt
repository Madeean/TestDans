package com.madeean.testdansmultipro.presentation.util

import android.text.Html
import android.text.Spanned

fun Int?.requireIfNull(defaultValue: Int = 0): Int {
  return this ?: defaultValue
}

fun Long?.requireIfNull(defaultValue: Long = 0L): Long {
  return this ?: defaultValue
}

fun Double?.requireIfNull(defaultValue: Double = 0.0): Double {
  return this ?: defaultValue
}

fun Float?.requireIfNull(defaultValue: Float = 0.0f): Float {
  return this ?: defaultValue
}

fun Boolean?.requireIfNull(defaultValue: Boolean = false): Boolean {
  return this ?: defaultValue
}

fun String?.requireIfNull(defaultValue: String = ""): String {
  return this ?: defaultValue
}

fun parseQueryString(query: String): Map<String, String> {
  return query.split("&").associate {
    val (key, value) = it.split("=")
    key to value
  }
}

fun htmlToString(html: String): String {
  val spanned: Spanned = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
  return spanned.toString()
}