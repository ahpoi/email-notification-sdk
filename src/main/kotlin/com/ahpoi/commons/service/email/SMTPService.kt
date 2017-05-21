package com.ahpoi.commons.service.email

import com.ahpoi.commons.service.email.model.Email
import com.ahpoi.commons.service.email.model.SMTPConfiguration
import com.ahpoi.commons.service.email.util.buildMessage
import org.slf4j.LoggerFactory
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport

class SMTPService(val config: SMTPConfiguration) :  EmailService {

    private val LOGGER = LoggerFactory.getLogger(SMTPService::class.java)

    private val session: Session

    init {
        LOGGER.info("Configuration for SMTP: {}", config)
        val auth = object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(config.userName, config.password)
            }
        }
        session = Session.getInstance(config.getProperties(), auth)
    }

    override fun send(email: Email): Boolean {
        try {
            Transport.send(buildMessage(
                    session = session,
                    senderEmail = config.senderEmail,
                    senderName = config.senderName,
                    email = email))
        } catch (e: Exception) {
            LOGGER.error("Exception occurred when sending email from SMTP Service", e)
            return false
        }
        LOGGER.info("Email sent successfully to {} with subject: {} from SMTP Server", email.recipient, email.subject)
        return true
    }

}


