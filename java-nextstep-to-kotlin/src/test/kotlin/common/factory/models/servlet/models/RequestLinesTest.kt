package common.factory.models.servlet.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class RequestLinesTest {
    @Nested
    @DisplayName("RequestLines.resource")
    inner class RequestLinesResourceTest {
        @Test
        fun `요청 헤더를 보고 동적 리소스 이름을 추출할 수 있다`() {
            val mockRequest = RequestLines(
                listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
            )

            assertEquals(
                mockRequest.resource(),
                "/user/create"
            )
        }

        @Test
        fun `요청 헤더를 보고 정적 리소스 이름을 추출할 수 있다`() {
            val mockRequest = RequestLines(
                listOf("GET /index.html HTTP/1.1")
            )

            assertEquals(
                mockRequest.resource(),
                "/index.html"
            )
        }
    }

    @Nested
    @DisplayName("RequestLines.method")
    inner class RequestLinesMethodTest {
        @Test
        fun `요청 헤더를 보고 메서드를 추출할 수 있다`() {
            val mockRequest = RequestLines(
                listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
            )

            assertEquals(
                mockRequest.method(),
                HttpMethod.GET
            )
        }

        @Test
        fun `요청 헤더를 보고 메서드를 추출할 수 있다2`() {
            val mockRequest = RequestLines(
                listOf("POST /index.html HTTP/1.1")
            )

            assertEquals(
                mockRequest.method(),
                HttpMethod.POST
            )
        }
    }

    @Nested
    @DisplayName("RequestLines.queryParameters")
    inner class RequestQueryParametersMethodTest {
        @Test
        fun `요청 헤더를 보고 Query Parameter 를 추출할 수 있다`() {
            val mockRequest = RequestLines(
                listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
            )

            val extractedQueryParameters: Map<String, Any> = assertDoesNotThrow(
                { mockRequest.queryParameters() }
            )

            assertNotNull(extractedQueryParameters)
            assertEquals(extractedQueryParameters["userId"], "test")
            assertEquals(extractedQueryParameters["password"], "1234")
        }
    }
}