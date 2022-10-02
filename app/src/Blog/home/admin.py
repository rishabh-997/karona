from django.contrib import admin
from .models import *
from notifications.base.admin import AbstractNotificationAdmin
from swapper import load_model

Notification = load_model('notifications', 'Notification')

#admin.site.register(Notification)
admin.site.register(BlogPost)
admin.site.register(Comment)
admin.site.register(Profile)
