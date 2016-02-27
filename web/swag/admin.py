from django.contrib import admin
from swag.models import *

class UserAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'email', 'phone']
    list_display_links = ['id', 'name', 'email']


admin.site.register(User, UserAdmin)
admin.site.register(Team)
admin.site.register(Show)
admin.site.register(TeamEvent)
admin.site.register(ShowEvent)
admin.site.register(FavoriteTeam)
admin.site.register(FavoriteShow)
