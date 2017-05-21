package service

import com.ahpoi.commons.service.email.SESService
import fixture.FixtureBuilder
import fixture.defaultSESConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test

@Ignore
class SESServiceIntegrationTest {

    @Test
    fun shouldSendEmailWithNoAttachments() {
        val sent = SESService(defaultSESConfiguration()).send(FixtureBuilder.defaultEmail())
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachments() {
        val sent = SESService(defaultSESConfiguration()).send(FixtureBuilder.defaultEmailWithAttachment())
        assertThat(sent).isEqualTo(true)
    }

}