package dev.kkkkkksssssaaaa.practice.kafka.checkout.controller

import dev.kkkkkksssssaaaa.practice.kafka.checkout.dto.CheckoutDto
import dev.kkkkkksssssaaaa.practice.kafka.checkout.service.SaveService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PostMapping

@Controller
class CheckoutSubmitController(
    private val saveService: SaveService
) {
    @PostMapping("/checkout/submit")
    fun doSubmit(checkoutDto: CheckoutDto, model: Model): String {
        println(checkoutDto)
        val checkoutId = saveService.save(checkoutDto)

        model.addAttribute("checkoutId", checkoutId)

        return "submitComplete"
    }
}