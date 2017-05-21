import com.ahpoi.commons.model.Attachment
import com.ahpoi.commons.model.Email
import com.ahpoi.commons.model.SMTPConfiguration
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import com.ahpoi.commons.service.SMTPService
import org.apache.commons.io.IOUtils
import org.assertj.core.api.Assertions.assertThat

@Ignore
class SMTPServiceTest {

    private lateinit var smtpConfigWithSSL: SMTPConfiguration

    private lateinit var smtpConfigWithTLS: SMTPConfiguration

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
                port = "587",
                userName = senderUsername,
                password = senderPassword,
                userTLS = true)
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

    fun defaultEmail() = Email(
            recipient = "integrationtest@mailinator.com",
            subject = "Integration Test Subject",
            content = "Integration Test Body")

    fun defaultEmailWithAttachment(): Email {
        val data = IOUtils.toByteArray(this.javaClass.classLoader.getResourceAsStream("dummy-attachment.txt"))
        val attachment = Attachment(fileName = "dummy-attachment.txt", data = data, mimeType = "application/plain")
        val email = defaultEmail().copy(attachments = arrayListOf(attachment))
        return email
    }

}