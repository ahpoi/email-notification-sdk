# Email-Notification-SDK

The purpose of this library is to hides the internal of configuring SMTP or SES to send emails.

This sdk is published by JitPack, to include: 

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
    	        compile 'com.github.ianahpo1095:email-notification-sdk:{{version}}'
    	}

```

To run tests you need to have a SMTP Server account and have those system properties set: 

```
-Drecipient.email="{to-be-replaced}"
-Dses.sender.email="{to-be-replaced}"
-Dsmtp.sender.email="{to-be-replaced}"
-Dsmtp.sender.username="{to-be-replaced}"
-Dsmtp.sender.password="{to-be-replaced}"

```
