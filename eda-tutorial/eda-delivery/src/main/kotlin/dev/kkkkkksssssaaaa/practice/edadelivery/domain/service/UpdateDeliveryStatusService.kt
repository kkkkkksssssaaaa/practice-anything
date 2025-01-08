package dev.kkkkkksssssaaaa.practice.edadelivery.domain.service

import dev.kkkkkksssssaaaa.practice.edadelivery.domain.enums.DeliveryStatus
import dev.kkkkkksssssaaaa.practice.edadelivery.domain.repository.DeliveryRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UpdateDeliveryStatusService(
    private val deliveryRepository: DeliveryRepository,
) {
    @Transactional
    @Scheduled(fixedRate = 10000)
    fun doUpdate() {
        deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED)
            .forEach {
                it.status = DeliveryStatus.IN_DELIVERY
            }

        println("======================== All status update: REQUESTED -> IN_DELIVERY ========================")

        deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY)
            .forEach {
                it.status = DeliveryStatus.COMPLETED
            }

        println("======================== All status update: IN_DELIVERY -> COMPLETED ========================")
    }
}