package common.factory.models.servlet.router

import common.factory.models.servlet.models.HandlerFunction
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class RoutesTest {
    @Nested
    inner class FindTest {
        private val mockRoutes = Routes()

        @BeforeEach
        fun setUp() {
            mockRoutes.putAll(
                mapOf(
                    "GET /user" to mockk<HandlerFunction>(),
                    "GET /user/{id}" to mockk<HandlerFunction>(),
                    "POST /user" to mockk<HandlerFunction>(),
                    "DELETE /user/{id}/order" to mockk<HandlerFunction>(),
                )
            )
        }

        @Test
        fun `시그니처를 통해 핸들러 함수를 찾을 수 있다`() {
            val signature = "GET /user"

            assertNotNull(mockRoutes[signature])
        }

        @Test
        fun `동적인 Path Variable 을 받는 핸들러 함수도 시그니처를 통해 찾을 수 있다`() {
            val signature = "GET /user/1"

            assertNotNull(mockRoutes[signature])
        }

        @Test
        fun `동적인 Path Variable 을 받는 핸들러 함수도 시그니처를 통해 찾을 수 있다2`() {
            val signature = "DELETE /user/6666/order"

            assertNotNull(mockRoutes[signature])
        }

        @Test
        fun `핸들러 함수를 찾을 수 없다면 null 을 반환한다`() {
            val signature = "PUT /user"

            assertNull(mockRoutes[signature])
        }
    }
}