package com.ahpoi.commons.service

import com.ahpoi.commons.model.Email
import java.io.UnsupportedEncodingException
import javax.activation.DataHandler
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart
import javax.mail.util.ByteArrayDataSource

abstract class AbstractEmailService {

    @Throws(MessagingException::class, UnsupportedEncodingException::class)
    protected fun buildMessage(session: Session, senderEmail: String, senderName: String, email: Email) =
            MimeMessage(session).apply {
                this.setFrom(InternetAddress(senderEmail, senderName))
                this.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email.recipient))
                this.subject = email.subject
                val multipart = addAttachments(email)
                val bodyPart = addMessageBody(email)
                multipart.addBodyPart(bodyPart)
                this.setContent(multipart)
            }

    @Throws(MessagingException::class)
    private fun addMessageBody(email: Email) = MimeBodyPart().apply {
        this.setContent(email.content, email.mimeType)
    }

    @Throws(MessagingException::class)
    private fun addAttachments(email: Email) = MimeMultipart().apply {
        for ((fileName, data, mimeType) in email.attachments) {
            val attachmentBodyPart = MimeBodyPart()
            val source = ByteArrayDataSource(data, mimeType)
            attachmentBodyPart.dataHandler = DataHandler(source)
            attachmentBodyPart.fileName = fileName
            this.addBodyPart(attachmentBodyPart)
        }
    }

}