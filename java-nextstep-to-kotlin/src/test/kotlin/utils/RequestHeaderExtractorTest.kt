package utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import utils.RequestHeaderExtractor.extractResourceName

class RequestHeaderExtractorTest {
    @Nested
    @DisplayName("RequestHeaderExtractor.extractResourceName")
    inner class ExtractResourceNameTest {
        @Test
        fun `최상위 리소스를 추출할 수 있다`() {
            val firstLine = "GET /index.html HTTP/1.1 "

            assertEquals("index.html", extractResourceName(firstLine))
        }

        @Test
        fun `depth 가 존재하는 리소스를 추출할 수 있다`() {
            val firstLine = "GET /js/bootstrap.js HTTP/1.1"

            assertEquals("js/bootstrap.js", extractResourceName(firstLine))
        }

        @Test
        fun `파일 명에 콤마가 포함되어 있어도 리소스를 추출할 수 있다`() {
            val firstLine = "GET /css/bootstrap.min.css HTTP/1.1"

            assertEquals("css/bootstrap.min.css", extractResourceName(firstLine))
        }
    }
}