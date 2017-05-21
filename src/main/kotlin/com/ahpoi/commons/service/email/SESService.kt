package com.ahpoi.commons.service.email

class SESService(val config: com.ahpoi.commons.service.email.model.SESConfiguration) : AbstractEmailService(), EmailService {

    private val LOGGER = org.slf4j.LoggerFactory.getLogger(com.ahpoi.commons.service.email.SESService::class.java)

    private val client = com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
            .standard()
            .withCredentials(com.amazonaws.auth.DefaultAWSCredentialsProviderChain())
            .withRegion(com.amazonaws.regions.Regions.US_EAST_1).build()

    init {
        LOGGER.info("Configuration for SES: {}", config)
    }

    override fun send(email: com.ahpoi.commons.service.email.model.Email): Boolean {
        try {
            java.io.ByteArrayOutputStream().use { outputStream ->
                val message = buildMessage(
                        session = javax.mail.Session.getDefaultInstance(java.util.Properties()),
                        senderEmail = config.senderEmail,
                        senderName = config.senderName,
                        email = email)
                message.writeTo(outputStream)
                val rawMessage = com.amazonaws.services.simpleemail.model.RawMessage(java.nio.ByteBuffer.wrap(outputStream.toByteArray()))
                val rawEmailRequest = com.amazonaws.services.simpleemail.model.SendRawEmailRequest(rawMessage)
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