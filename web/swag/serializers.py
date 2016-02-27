from rest_framework.serializers import ModelSerializer, PrimaryKeyRelatedField
from swag.models import *


class UserSerializer(ModelSerializer):

    class Meta:
        model = User
        resource_name = 'users'


class TeamSerializer(ModelSerializer):

    class Meta:
        model = Team
        resource_name = 'teams'


class ShowSerializer(ModelSerializer):

    class Meta:
        model = Show
        resource_name = 'shows'


class FavoriteTeamSerializer(ModelSerializer):

    class Meta:
        model = FavoriteTeam
        resource_name = 'favoriteteams'
        depth = 2


class FavoriteTeamPostSerializer(ModelSerializer):
    user = PrimaryKeyRelatedField(queryset=User.objects.all())
    team = PrimaryKeyRelatedField(queryset=Team.objects.all())

    class Meta:
        model = FavoriteTeam
        resource_name = 'favoriteteams'


class FavoriteShowSerializer(ModelSerializer):

    class Meta:
        model = FavoriteShow
        resource_name = 'favoriteshows'
        depth = 2


class FavoriteShowPostSerializer(ModelSerializer):
    user = PrimaryKeyRelatedField(queryset=User.objects.all())
    show = PrimaryKeyRelatedField(queryset=Show.objects.all())

    class Meta:
        model = FavoriteShow
        resource_name = 'favoriteshows'


class GameSerializer(ModelSerializer):

    class Meta:
        model = Game
        resource_name = 'games'
        depth = 2


class EpisodeSerializer(ModelSerializer):

    class Meta:
        model = Episode
        resource_name = 'episodes'
        depth = 2
