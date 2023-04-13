package ads_std

import java.io.File
import java.io.FileOutputStream

fun fileToLinesList(file: File): List<String> {
    return file.useLines { it.toList() }
}

fun fileAppendString(file: File, string: String) {
    FileOutputStream(file, true).bufferedWriter().use { out ->
        out.append(string)
        out.newLine()
    }
}