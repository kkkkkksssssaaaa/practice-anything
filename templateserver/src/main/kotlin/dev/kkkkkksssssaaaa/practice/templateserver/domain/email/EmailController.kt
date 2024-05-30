package dev.kkkkkksssssaaaa.practice.templateserver.domain.email

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class EmailController(
    private val emailService: EmailService,
) {
    @PostMapping("/send")
    fun sendMail(
        @RequestBody request: SendMailRequest
    ): ResponseEntity<String> {
        emailService.sendEmail(
            request.templateId,
            request.recipient,
            request.properties
        )

        return ResponseEntity.ok("Email sent successfully")
    }
}