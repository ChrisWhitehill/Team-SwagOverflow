from django.contrib import admin
from swag.models import *

class UserAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'email', 'phone']
    list_display_links = ['id', 'name', 'email']


class TeamAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'league']
    list_display_links = ['id', 'name']


class ShowAdmin(admin.ModelAdmin):
    list_display = ['id', 'name']
    list_display_links = ['id', 'name']


class GameAdmin(admin.ModelAdmin):
    list_display = ['id', 'away_team', 'home_team', 'date', 'channel_name', 'channel_number']


class EpisodeAdmin(admin.ModelAdmin):
    list_display = ['id', 'show', 'date', 'channel_name', 'channel_number']


class FavoriteTeamAdmin(admin.ModelAdmin):
    list_display = ['id', 'team', 'user']


class FavoriteShowAdmin(admin.ModelAdmin):
    list_display = ['id', 'show', 'user']


admin.site.register(User, UserAdmin)
admin.site.register(Team, TeamAdmin)
admin.site.register(Show, ShowAdmin)
admin.site.register(Game, GameAdmin)
admin.site.register(Episode, EpisodeAdmin)
admin.site.register(FavoriteTeam, FavoriteTeamAdmin)
admin.site.register(FavoriteShow, FavoriteShowAdmin)
