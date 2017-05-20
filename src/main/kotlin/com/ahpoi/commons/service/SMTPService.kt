package com.ahpoi.commons.service

import com.ahpoi.commons.model.Email
import com.ahpoi.commons.model.SMTPConfiguration
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport

class SMTPService(val config: SMTPConfiguration) : AbstractEmailService(), EmailService {

    private val session: Session

    init {
        val auth = object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(config.userName, config.password)
            }
        }
        session = Session.getInstance(config.getProperties(), auth)
    }

    override fun send(email: Email): Boolean {

            Transport.send(buildMessage(
                    session = session,
                    senderEmail = config.senderEmail,
                    senderName = config.senderName,
                    email = email))

        return true
    }

}


