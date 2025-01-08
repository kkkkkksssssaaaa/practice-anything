package dev.kkkkkksssssaaaa.practice.edamember.edamember.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "`user`")
class UserEntity(
    loginId: String,
    name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(unique = true)
    var loginId: String = loginId

    @Column
    var name: String = name
}