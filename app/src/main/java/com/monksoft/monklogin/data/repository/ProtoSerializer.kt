package com.monksoft.monklogin.data.repository

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.monksoft.monklogin.Monk
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class ProtoSerializer : Serializer<Monk> {
    override fun readFrom(input: InputStream): Monk {
        try {
            return Monk.parseFrom(input)
        } catch (e : InvalidProtocolBufferException) {
            throw CorruptionException("Cannon read proto", e)
        }
    }

    override fun writeTo(t: Monk, output: OutputStream) {
        t.writeTo(output)
    }
}