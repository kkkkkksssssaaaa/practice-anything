package dev.kkkkkksssssaaaa.practice.templateserver.common.log

import dev.kkkkkksssssaaaa.practice.templateserver.common.log.entity.Log
import dev.kkkkkksssssaaaa.practice.templateserver.domain.email.EmailSender
import dev.kkkkkksssssaaaa.practice.templateserver.domain.notification.NotificationEventMessage
import dev.kkkkkksssssaaaa.practice.templateserver.domain.push.PushSender
import dev.kkkkkksssssaaaa.practice.templateserver.domain.sms.SmsSender
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Aspect
@Service
class NotificationLoggingService(
    private val repository: LogRepository
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @After(
        "execution(* dev.kkkkkksssssaaaa.practice.templateserver.domain.*.*NotificationSender.doSend*(..))"
    )
    @Transactional
    fun doLogging(joinPoint: JoinPoint) {
        log.info("invoke aop event")

        val eventMessage = joinPoint.args[0] as NotificationEventMessage

        repository.save(
            Log(
                subject = eventMessage.subject,
                content = eventMessage.content,
                target = eventMessage.target,
                platform = getPlatformType(joinPoint.sourceLocation.withinType.simpleName)
            )
        )
    }

    private fun getPlatformType(simpleClassName: String): String {
        return when (simpleClassName) {
            EmailSender::class.simpleName -> "EMAIL"
            SmsSender::class.simpleName -> "SMS"
            PushSender::class.simpleName -> "PUSH"
            else -> throw IllegalArgumentException("Not support platform")
        }
    }
}