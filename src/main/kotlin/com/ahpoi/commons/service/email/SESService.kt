package com.ahpoi.commons.service.email

import com.ahpoi.commons.service.email.model.Email
import com.ahpoi.commons.service.email.model.SESConfiguration
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.RawMessage
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest
import org.slf4j.LoggerFactory
import java.nio.ByteBuffer
import javax.mail.Session

class SESService(val config: SESConfiguration) : AbstractEmailService(), EmailService {

    private val LOGGER = LoggerFactory.getLogger(SESService::class.java)

    private val client = AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withCredentials(DefaultAWSCredentialsProviderChain())
            .withRegion(Regions.US_EAST_1).build()

    init {
        LOGGER.info("Configuration for SES: {}", config)
    }

    override fun send(email: Email): Boolean {
        try {
            java.io.ByteArrayOutputStream().use { outputStream ->
                val message = buildMessage(
                        session = Session.getDefaultInstance(java.util.Properties()),
                        senderEmail = config.senderEmail,
                        senderName = config.senderName,
                        email = email)
                message.writeTo(outputStream)
                val rawMessage = RawMessage(ByteBuffer.wrap(outputStream.toByteArray()))
                val rawEmailRequest = SendRawEmailRequest(rawMessage)
                client.sendRawEmail(rawEmailRequest)
            }
        } catch (e: Exception) {
            LOGGER.error("Exception occurred when sending email from SES Service", e)
            return false
        }
        LOGGER.info("Email sent successfully to {} with subject: {} from SES Server", email.recipient, email.subject)
        return true
    }

}