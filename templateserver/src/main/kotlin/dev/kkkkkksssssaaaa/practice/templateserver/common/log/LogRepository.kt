package dev.kkkkkksssssaaaa.practice.templateserver.common.log

import dev.kkkkkksssssaaaa.practice.templateserver.common.log.entity.Log
import org.springframework.data.jpa.repository.JpaRepository

interface LogRepository: JpaRepository<Log, Long>