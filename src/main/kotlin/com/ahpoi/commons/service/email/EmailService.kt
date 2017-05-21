package com.ahpoi.commons.service.email

interface EmailService {

    fun send(email: com.ahpoi.commons.service.email.model.Email): Boolean

}