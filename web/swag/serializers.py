from rest_framework.serializers import ModelSerializer
from swag.models import User, Team, Show, FavoriteTeam, FavoriteShow, TeamEvent, ShowEvent


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


class FavoriteShowSerializer(ModelSerializer):

    class Meta:
        model = FavoriteShow


class TeamEventSerializer(ModelSerializer):

    class Meta:
        model = TeamEvent


class ShowEventSerializer(ModelSerializer):

    class Meta:
        model = ShowEvent
