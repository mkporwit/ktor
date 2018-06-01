package io.ktor.compat

import kotlinx.io.charsets.*


expect fun Char.isLowerCase(): Boolean

expect fun String.toCharArray(): CharArray

expect fun String.toByteArray(charset: Charset = Charsets.UTF_8): ByteArray

expect fun String(bytes: ByteArray, offset: Int = 0, count: Int = bytes.size, charset: Charset = Charsets.UTF_8): String

expect fun encodeBase64(string: String, charset: Charset = Charsets.UTF_8): String

expect fun decodeBase64(string: String, charset: Charset = Charsets.UTF_8): String
