package dev.kkkkkksssssaaaa.practice.realtimealarmsystem.common.entity

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.springframework.beans.factory.annotation.Value
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Converter
class AesConverter(
    @Value("\${app.crypto.key}") private val key: String,
    @Value("\${app.crypto.iv}") private val iv: String,
) : AttributeConverter<String, String> {
    companion object {
        private const val ALGORITHM = "AES/CBC/PKCS5Padding"
    }

    override fun convertToDatabaseColumn(attribute: String?): String? {
        if (attribute == null) {
            return null
        }

        return try {
            val cipher = Cipher.getInstance(ALGORITHM)
            val keySpec = SecretKeySpec(key.toByteArray(), "AES")
            val ivParameterSpec = IvParameterSpec(iv.toByteArray())
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec)
            val encrypted = cipher.doFinal(attribute.toByteArray(StandardCharsets.UTF_8))

            Base64.getUrlEncoder().encodeToString(encrypted)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun convertToEntityAttribute(dbData: String?): String? {
        if (dbData == null) {
            return null
        }

        return try {
            val cipher = Cipher.getInstance(ALGORITHM)
            val keySpec = SecretKeySpec(key.toByteArray(), "AES")
            val ivParameterSpec = IvParameterSpec(iv.toByteArray())

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec)

            cipher.doFinal(
                Base64.getUrlDecoder().decode(dbData),
            ).toString(StandardCharsets.UTF_8)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}