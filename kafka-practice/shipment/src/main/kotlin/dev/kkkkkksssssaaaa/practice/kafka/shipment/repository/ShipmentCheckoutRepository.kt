package dev.kkkkkksssssaaaa.practice.kafka.shipment.repository

import dev.kkkkkksssssaaaa.practice.kafka.shipment.entity.ShipmentCheckoutEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShipmentCheckoutRepository: JpaRepository<ShipmentCheckoutEntity, Long>