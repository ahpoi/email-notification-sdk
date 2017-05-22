package com.ahpoi.commons.service.email.model;

import java.util.ArrayList;
import java.util.List;

public final class EmailBuilder {

    private String recipient;

    private String subject;

    private String content;

    private String mimeType;

    private List<Attachment> attachments;

    public EmailBuilder withRecipient(String recipient) {
        this.recipient = recipient;
        return this;
    }

    public EmailBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public EmailBuilder withMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public EmailBuilder withAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Email build() {
        if (attachments == null) {
            this.attachments = new ArrayList<>();
        }
        if (mimeType == null || mimeType.isEmpty()) {
            this.mimeType = "text/html; charset=utf-8";
        }
        return new Email(recipient, subject, content, mimeType, attachments);
    }
}
