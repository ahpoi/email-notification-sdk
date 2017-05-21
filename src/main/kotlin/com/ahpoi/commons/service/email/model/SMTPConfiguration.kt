package com.ahpoi.commons.service.email.model

import java.util.Properties

data class SMTPConfiguration(val senderEmail: String,
                             val senderName: String,
                             val host: String,
                             val port: String,
                             val userName: String,
                             val password: String,
                             val useSSL: Boolean = false,
                             val useTLS: Boolean = false) {

    fun getProperties() = Properties().apply {
        this.put("mail.smtp.host", host)
        this.put("mail.smtp.port", port)
        this.put("mail.smtp.auth", "true")
        when {
            useSSL && useTLS -> throw RuntimeException("Both SSL and TLS cannot be enabled")
            useTLS -> this.put("mail.smtp.starttls.enable", "true")
            useSSL -> {
                this.put("mail.smtp.socketFactory.port", host)
                this.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            }
        }
    }

    override fun toString(): String {
        return "SMTPConfiguration(" +
                "senderEmail='$senderEmail', " +
                "senderName='$senderName', " +
                "host='$host', " +
                "port='$port', " +
                "userName='$userName', " +
                "password='********', " +
                "useSSL=$useSSL, " +
                "useTLS=$useTLS)"
    }

}