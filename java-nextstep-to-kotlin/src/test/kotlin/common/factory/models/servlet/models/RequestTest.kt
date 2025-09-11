package common.factory.models.servlet.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class RequestTest {
    @Nested
    @DisplayName("RequestLines.resource")
    inner class RequestResourceTest {
        @Test
        fun `요청 헤더를 보고 동적 리소스 이름을 추출할 수 있다`() {
            val mockRequest = Request(
                header = RequestHeader(
                    listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
                ),
                body = null
            )

            assertEquals(
                mockRequest.header.resource(),
                "/user/create"
            )
        }

        @Test
        fun `요청 헤더를 보고 정적 리소스 이름을 추출할 수 있다`() {
            val mockRequest = Request(
                header = RequestHeader(listOf("GET /index.html HTTP/1.1")),
                body = null,
            )

            assertEquals(
                mockRequest.header.resource(),
                "/index.html"
            )
        }
    }

    @Nested
    @DisplayName("RequestLines.method")
    inner class RequestMethodTest {
        @Test
        fun `요청 헤더를 보고 메서드를 추출할 수 있다`() {
            val mockRequest = Request(
                header = RequestHeader(
                    listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
                ),
                body = null
            )

            assertEquals(
                mockRequest.header.method(),
                HttpMethod.GET
            )
        }

        @Test
        fun `요청 헤더를 보고 메서드를 추출할 수 있다2`() {
            val mockRequest = Request(
                header = RequestHeader(listOf("POST /index.html HTTP/1.1")),
                body = null
            )

            assertEquals(
                mockRequest.header.method(),
                HttpMethod.POST
            )
        }
    }

    @Nested
    @DisplayName("RequestLines.queryParameters")
    inner class RequestQueryParametersMethodTest {
        @Test
        fun `요청 헤더를 보고 Query Parameter 를 추출할 수 있다`() {
            val mockRequest = Request(
                header = RequestHeader(
                    listOf("GET /user/create?userId=test&password=1234&name=testName&email=kofc312%40naver.com HTTP/1.1")
                ),
                body = null
            )

            val extractedQueryParameters: Map<String, Any>? = assertDoesNotThrow(
                { mockRequest.header.queryParameters() }
            )

            assertNotNull(extractedQueryParameters)
            assertEquals(extractedQueryParameters!!["userId"], "test")
            assertEquals(extractedQueryParameters["password"], "1234")
        }
    }
}



