package fixture

import com.ahpoi.commons.model.Email
import org.apache.commons.io.IOUtils

object FixtureBuilder {

    fun defaultEmail() = Email(
            recipient = System.getProperties().getProperty("recipient.email"),
            subject = "Integration Test Subject",
            content = "Integration Test Body")

    fun defaultEmailWithAttachment(): Email {
        val data = IOUtils.toByteArray(this.javaClass.classLoader.getResourceAsStream("dummy-attachment.txt"))
        val attachment = com.ahpoi.commons.model.Attachment(fileName = "dummy-attachment.txt", data = data, mimeType = "application/plain")
        val email = defaultEmail().copy(attachments = arrayListOf(attachment))
        return email
    }

}
