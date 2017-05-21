# Email-Notification-SDK

Email SDK that is used to send email using SMTP or Amazon SES.

The purpose of this library is to hides the internal of configuring SMTP or SES to send emails.

To run tests you need to have a Gmail SMTP Server account and have those system properties set: 

```
-Drecipient.email="{to-be-replaced}"
-Dses.sender.email="{to-be-replaced}"
-Dsmtp.sender.email="{to-be-replaced}"
-Dsmtp.sender.username="{to-be-replaced}"
-Dsmtp.sender.password="{to-be-replaced}"

```
