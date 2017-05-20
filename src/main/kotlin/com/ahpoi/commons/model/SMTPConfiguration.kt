package com.ahpoi.commons.model

import java.util.*

/**
 * Created by ahpoi on 20/5/17.
 */
data class SMTPConfiguration(val senderEmail: String,
                             val senderName: String,
                             val host: String,
                             val port: String,
                             val userName: String,
                             val password: String,
                             val useSSL: Boolean = false,
                             val userTLS: Boolean = false) {

    fun getProperties() = Properties().apply {
        this.put("mail.smtp.host", host)
        this.put("mail.smtp.port", port)
        this.put("mail.smtp.auth", "true")
        when {
            userTLS -> this.put("mail.smtp.starttls.enable", "true")
            useSSL -> {
                this.put("mail.smtp.socketFactory.port", host)
                this.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            }
        }
    }

}