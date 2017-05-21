import com.ahpoi.commons.model.Email
import com.ahpoi.commons.model.SMTPConfiguration
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import com.ahpoi.commons.service.SMTPService
import org.assertj.core.api.Assertions.assertThat

@Ignore
class SMTPServiceTest {

    private lateinit var smtpConfigWithSSL: SMTPConfiguration

    private lateinit var smtpConfigWithTLS: SMTPConfiguration

    private val email = Email(recipient = "wiceawk5@trashcanmail.com", subject = "Integration Test Subject", content = "Integration Test Body")

    @Before
    fun setUp() {
        val senderEmail = System.getProperties().getProperty("smtp.sender.email")
        val senderUsername = System.getProperties().getProperty("smtp.sender.username")
        val senderPassword = System.getProperties().getProperty("smtp.sender.password")
        smtpConfigWithSSL = SMTPConfiguration(senderEmail = senderEmail,
                senderName = "Integration Test",
                host = "smtp.gmail.com",
                port = "465",
                userName = senderUsername,
                password = senderPassword,
                useSSL = true)

        smtpConfigWithTLS = SMTPConfiguration(senderEmail = senderEmail,
                senderName = "Integration Test",
                host = "smtp.gmail.com",
                port = "465",
                userName = senderUsername,
                password = senderPassword,
                useSSL = true)
    }

    @Test
    fun shouldSendEmailWithNoAttachmentsSSL() {
        val sent = SMTPService(smtpConfigWithSSL).send(email)
        assertThat(sent).isEqualTo(true)
    }

    @Test
    fun shouldSendEmailWithAttachmentsSSL() {

    }

    @Test
    fun shouldSendEmailWithNoAttachmentsTLS() {

    }

    @Test
    fun shouldSendEmailWithAttachmentsTLS() {

    }

}