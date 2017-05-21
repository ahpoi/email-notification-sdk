package com.ahpoi.commons.service.email

abstract class AbstractEmailService {

    @Throws(javax.mail.MessagingException::class, java.io.UnsupportedEncodingException::class)
    protected fun buildMessage(session: javax.mail.Session, senderEmail: String, senderName: String, email: com.ahpoi.commons.service.email.model.Email) =
            javax.mail.internet.MimeMessage(session).apply {
                this.setFrom(javax.mail.internet.InternetAddress(senderEmail, senderName))
                this.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email.recipient))
                this.subject = email.subject
                val multipart = addAttachments(email)
                val bodyPart = addMessageBody(email)
                multipart.addBodyPart(bodyPart)
                this.setContent(multipart)
            }

    @Throws(javax.mail.MessagingException::class)
    private fun addMessageBody(email: com.ahpoi.commons.service.email.model.Email) = javax.mail.internet.MimeBodyPart().apply {
        this.setContent(email.content, email.mimeType)
    }

    @Throws(javax.mail.MessagingException::class)
    private fun addAttachments(email: com.ahpoi.commons.service.email.model.Email) = javax.mail.internet.MimeMultipart().apply {
        for ((fileName, data, mimeType) in email.attachments) {
            val attachmentBodyPart = javax.mail.internet.MimeBodyPart()
            val source = javax.mail.util.ByteArrayDataSource(data, mimeType)
            attachmentBodyPart.dataHandler = javax.activation.DataHandler(source)
            attachmentBodyPart.fileName = fileName
            this.addBodyPart(attachmentBodyPart)
        }
    }

}