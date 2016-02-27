from rest_framework.serializers import ModelSerializer
from swag.models import *


class UserSerializer(ModelSerializer):

    class Meta:
        model = User


class TeamSerializer(ModelSerializer):

    class Meta:
        model = Team


class ShowSerializer(ModelSerializer):

    class Meta:
        model = Show


class FavoriteTeamSerializer(ModelSerializer):

    class Meta:
        model = FavoriteTeam
        depth = 2


class FavoriteShowSerializer(ModelSerializer):

    class Meta:
        model = FavoriteShow
        depth = 2


class GameSerializer(ModelSerializer):

    class Meta:
        model = Game


class EpisodeSerializer(ModelSerializer):

    class Meta:
        model = Episode
