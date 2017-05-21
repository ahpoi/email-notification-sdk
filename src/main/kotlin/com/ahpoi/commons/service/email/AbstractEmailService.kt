package com.ahpoi.commons.service.email

import com.ahpoi.commons.service.email.model.Email
import java.io.UnsupportedEncodingException
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

abstract class AbstractEmailService {

    @Throws(MessagingException::class, UnsupportedEncodingException::class)
    protected fun buildMessage(session: Session, senderEmail: String, senderName: String, email: Email) =
           MimeMessage(session).apply {
                this.setFrom(javax.mail.internet.InternetAddress(senderEmail, senderName))
                this.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email.recipient))
                this.subject = email.subject
                val multipart = addAttachments(email)
                val bodyPart = addMessageBody(email)
                multipart.addBodyPart(bodyPart)
                this.setContent(multipart)
            }

    @Throws(javax.mail.MessagingException::class)
    private fun addMessageBody(email: Email) = javax.mail.internet.MimeBodyPart().apply {
        this.setContent(email.content, email.mimeType)
    }

    @Throws(MessagingException::class)
    private fun addAttachments(email: Email) = MimeMultipart().apply {
        for ((fileName, data, mimeType) in email.attachments) {
            val attachmentBodyPart = javax.mail.internet.MimeBodyPart()
            val source = javax.mail.util.ByteArrayDataSource(data, mimeType)
            attachmentBodyPart.dataHandler = javax.activation.DataHandler(source)
            attachmentBodyPart.fileName = fileName
            this.addBodyPart(attachmentBodyPart)
        }
    }

}