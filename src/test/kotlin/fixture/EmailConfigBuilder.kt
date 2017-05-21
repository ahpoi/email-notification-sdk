package fixture

import com.ahpoi.commons.service.email.model.SESConfiguration
import com.ahpoi.commons.service.email.model.SMTPConfiguration

private val senderName = "Integration Test"
private val sesSenderEmail = System.getProperties().getProperty("ses.sender.email")!!
private val smtpSenderEmail = System.getProperties().getProperty("smtp.sender.email")!!
private val smtpSenderUsername = System.getProperties().getProperty("smtp.sender.username")!!
private val smtpSenderPassword = System.getProperties().getProperty("smtp.sender.password")!!

fun defaultSESConfiguration(): SESConfiguration {
    return SESConfiguration(senderEmail = sesSenderEmail, senderName = senderName)
}

fun defaultSMTPConfigurationSSL(): SMTPConfiguration {
    return SMTPConfiguration(senderEmail = smtpSenderEmail,
            senderName = senderName,
            host = "smtp.gmail.com",
            port = "465",
            userName = smtpSenderUsername,
            password = smtpSenderPassword,
            useSSL = true)
}

fun defaultSMTPConfigurationTLS(): SMTPConfiguration {
    return SMTPConfiguration(senderEmail = smtpSenderEmail,
            senderName = senderName,
            host = "smtp.gmail.com",
            port = "587",
            userName = smtpSenderUsername,
            password = smtpSenderPassword,
            useTLS = true)
}