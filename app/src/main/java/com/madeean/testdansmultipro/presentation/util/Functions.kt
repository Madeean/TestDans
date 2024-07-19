package com.madeean.testdansmultipro.presentation.util

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