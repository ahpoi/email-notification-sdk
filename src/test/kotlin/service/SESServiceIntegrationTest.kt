package service

import com.ahpoi.commons.service.email.model.SESConfiguration
import com.ahpoi.commons.service.email.SESService
import fixture.FixtureBuilder
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Ignore
class SESServiceIntegrationTest {

    private lateinit var sesConfiguration: SESConfiguration

    @Before
    fun setUp() {
        val senderEmail = System.getProperties().getProperty("ses.sender.email")
        val senderName = "Integration Test"
        sesConfiguration = SESConfiguration(senderEmail = senderEmail, senderName = senderName)
    }

    @Test
    fun shouldSendEmailWithNoAttachments() {
        val sent = SESService(sesConfiguration).send(FixtureBuilder.defaultEmail())
        Assertions.assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachments() {
        val sent = SESService(sesConfiguration).send(FixtureBuilder.defaultEmailWithAttachment())
        Assertions.assertThat(sent).isEqualTo(true)
    }

}