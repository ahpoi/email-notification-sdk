package com.ahpoi.commons.service

import com.ahpoi.commons.model.Email

interface EmailService {

    fun send(email: Email): Boolean

}