package com.ahpoi.commons.service.email

import com.ahpoi.commons.service.email.model.Email

interface EmailService {

    fun send(email: Email): Boolean

}