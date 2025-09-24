package common.factory.models.servlet.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RequestHeaderTest {
    @Nested
    @DisplayName("RequestHeader.contentLength")
    inner class ContentLengthTest {
        @Test
        fun `헤더에 Content-Length 가 있다면 값을 Int 로 추출할 수 있다`() {
            val header = RequestHeader(
                listOf("Content-Length: 1")
            )

            assertEquals(1, header.contentLength())
        }

        @Test
        fun `헤더에 대소문자와 관계 없이 Content-Length 가 있다면 값을 Int 로 추출할 수 있다`() {
            val header = RequestHeader(
                listOf("content-length: 1")
            )

            assertEquals(1, header.contentLength())
        }
    }
}