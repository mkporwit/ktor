package io.ktor.compat

import kotlinx.io.charsets.*
import java.util.*

actual fun Char.isLowerCase(): Boolean = Character.isLowerCase(this)

actual fun String.toCharArray(): CharArray = (this as java.lang.String).toCharArray()

actual fun String.toByteArray(charset: Charset): ByteArray =
    (this as java.lang.String).getBytes(java.nio.charset.Charset.forName(charset.name))

actual fun String(
    bytes: ByteArray,
    offset: Int,
    count: Int,
    charset: Charset
): String = java.lang.String(bytes, offset, count, java.nio.charset.Charset.forName(charset.name)) as String

actual fun encodeBase64(string: String, charset: Charset): String =
    Base64.getEncoder().encodeToString(string.toByteArray(charset))

actual fun decodeBase64(string: String, charset: Charset): String =
    String(Base64.getDecoder().decode(string), charset = charset)