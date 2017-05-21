package service

import com.ahpoi.commons.service.email.SMTPService
import fixture.FixtureBuilder.defaultEmail
import fixture.FixtureBuilder.defaultEmailWithAttachment
import fixture.defaultSMTPConfigurationSSL
import fixture.defaultSMTPConfigurationTLS
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

@Ignore
class SMTPServiceIntegrationTest {

    @Test
    fun shouldSendEmailWithNoAttachmentsSSL() {
        val sent = SMTPService(defaultSMTPConfigurationSSL()).send(defaultEmail())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachmentsSSL() {
        val sent = SMTPService(defaultSMTPConfigurationSSL()).send(defaultEmailWithAttachment())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithNoAttachmentsTLS() {
        val sent = SMTPService(defaultSMTPConfigurationTLS()).send(defaultEmail())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachmentsTLS() {
        val sent = SMTPService(defaultSMTPConfigurationTLS()).send(defaultEmailWithAttachment())
        assertThat(sent).isEqualTo(true)
    }
}