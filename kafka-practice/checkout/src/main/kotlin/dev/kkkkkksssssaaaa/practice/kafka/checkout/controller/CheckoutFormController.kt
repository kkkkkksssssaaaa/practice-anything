package dev.kkkkkksssssaaaa.practice.kafka.checkout.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CheckoutFormController {
    @GetMapping("/checkout/form")
    fun checkoutForm(model: Model): String {
        println("checkoutform.....")

        return "checkoutForm"
    }
}