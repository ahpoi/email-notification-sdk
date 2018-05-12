package com.ahpoi.commons.service.email

import com.ahpoi.commons.service.email.model.Email
import com.ahpoi.commons.service.email.model.SESConfiguration
import com.ahpoi.commons.service.email.util.buildMessage
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.RawMessage
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.Properties
import javax.mail.Session

private val LOGGER = LoggerFactory.getLogger(SMTPService::class.java)

class SESService(private val config: SESConfiguration) : EmailService {

    private val client = AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withCredentials(DefaultAWSCredentialsProviderChain())
            .withRegion(Regions.US_EAST_1).build()

    override fun send(email: Email): Boolean {
        try {
            ByteArrayOutputStream().use { outputStream ->
                val message = buildMessage(
                        session = Session.getDefaultInstance(Properties()),
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
        LOGGER.info("Email sent successfully from SES Service")
        return true
    }

}