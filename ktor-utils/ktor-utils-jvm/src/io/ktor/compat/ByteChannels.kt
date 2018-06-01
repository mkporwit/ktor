package io.ktor.compat

import kotlinx.coroutines.experimental.io.*
import kotlinx.io.core.*


suspend fun ByteReadChannel.readRemaining(): ByteReadPacket = readRemaining(Long.MAX_VALUE)