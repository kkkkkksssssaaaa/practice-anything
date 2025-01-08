package dev.kkkkkksssssaaaa.practice.edadelivery.domain.entity

import jakarta.persistence.*

@Entity
@Table(indexes = [Index(name = "idx_user_id", columnList = "user_id")])
class UserAddress(
    userId: Long,
    address: String,
    alias: String?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    val userId: Long = userId

    @Column
    val address: String = address

    @Column
    val alias: String? = alias
}