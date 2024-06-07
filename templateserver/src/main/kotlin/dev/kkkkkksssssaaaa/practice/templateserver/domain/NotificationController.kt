package dev.kkkkkksssssaaaa.practice.templateserver.domain

import dev.kkkkkksssssaaaa.practice.templateserver.domain.email.SendNotificationRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val service: NotificationService
) {
    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendMail(
        @RequestBody request: SendNotificationRequest
    ): ResponseEntity<String> {
        service.send(request)

        return ResponseEntity.ok("Notification sent successfully")
    }
}