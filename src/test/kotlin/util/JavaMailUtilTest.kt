package util

import com.ahpoi.commons.service.email.model.Email
import com.ahpoi.commons.service.email.util.buildMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*
import javax.mail.Session

class JavaMailUtilTest {

    @Test
    fun shouldBuildMessage() {
        val email = Email(recipient = "recipient@gmail.com", subject = "My Integration Test", content = "Test 123")
        val message = buildMessage(session = Session.getDefaultInstance(Properties()),
                senderEmail = "test@gmail.com",
                senderName = "Integration Test",
                email = email)
        assertThat(message.getHeader("from")).isEqualTo(arrayOf("Integration Test <test@gmail.com>"))
        assertThat(message.getHeader("To")).isEqualTo(arrayOf("recipient@gmail.com"))
        assertThat(message.subject).isEqualTo("My Integration Test")
        assertThat(message.content).isNotNull()
    }

}