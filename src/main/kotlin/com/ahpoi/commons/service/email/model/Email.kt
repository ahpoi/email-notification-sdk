package com.ahpoi.commons.service.email.model

import kotlin.collections.ArrayList

data class Email(val recipient: String,
                 val subject: String,
                 val content: String,
                 val mimeType: String = "text/html; charset=utf-8",
                 val attachments: List<Attachment> = ArrayList())

data class Attachment(val fileName: String, val data: ByteArray, val mimeType: String)
