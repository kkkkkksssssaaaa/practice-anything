package dev.kkkkkksssssaaaa.practice.kafka.checkout.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CheckoutSubmitControllerTest {
    @Autowired
    private var mockMvc: MockMvc? = null

    @Test
    fun doTest() {
        mockMvc?.perform(
            MockMvcRequestBuilders.post("/checkout/submit")
                .param("memberId", "10001")
                .param("productId", "2000001")
                .param("amount", "30000")
                .param("shippingAddress", "546"))
            ?.andExpect(status().isOk())
            ?.andDo(print())
    }
}