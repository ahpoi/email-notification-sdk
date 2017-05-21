package service

import com.ahpoi.commons.service.email.model.SMTPConfiguration
import com.ahpoi.commons.service.email.SMTPService
import fixture.FixtureBuilder.defaultEmail
import fixture.FixtureBuilder.defaultEmailWithAttachment
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

@Ignore
class SMTPServiceIntegrationTest {

    private lateinit var smtpConfigWithSSL: SMTPConfiguration

    private lateinit var smtpConfigWithTLS: SMTPConfiguration

    @Test
    fun setUp() {
        val senderEmail = System.getProperties().getProperty("smtp.sender.email")
        val senderName = "Integration Test"
        val senderUsername = System.getProperties().getProperty("smtp.sender.username")
        val senderPassword = System.getProperties().getProperty("smtp.sender.password")
        smtpConfigWithSSL = SMTPConfiguration(senderEmail = senderEmail,
                senderName = senderName,
                host = "smtp.gmail.com",
                port = "465",
                userName = senderUsername,
                password = senderPassword,
                useSSL = true)

        smtpConfigWithTLS = SMTPConfiguration(senderEmail = senderEmail,
                senderName = senderName,
                host = "smtp.gmail.com",
                port = "587",
                userName = senderUsername,
                password = senderPassword,
                useTLS = true)
    }

    @Test
    fun shouldSendEmailWithNoAttachmentsSSL() {
        val sent = SMTPService(smtpConfigWithSSL).send(defaultEmail())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachmentsSSL() {
        val sent = SMTPService(smtpConfigWithSSL).send(defaultEmailWithAttachment())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithNoAttachmentsTLS() {
        val sent = SMTPService(smtpConfigWithTLS).send(defaultEmail())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachmentsTLS() {
        val sent = SMTPService(smtpConfigWithTLS).send(defaultEmailWithAttachment())
        assertThat(sent).isEqualTo(true)
    }
}