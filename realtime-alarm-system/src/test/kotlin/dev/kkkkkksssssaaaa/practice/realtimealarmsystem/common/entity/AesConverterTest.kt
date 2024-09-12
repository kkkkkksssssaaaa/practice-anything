package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

class AesConverterTest {
    @Test
    fun `random uuid 생성`() {
        println(UUID.randomUUID().toString().replace("-", "").substring(0, 32))
        println(UUID.randomUUID().toString().replace("-", "").substring(0, 16))
    }
}