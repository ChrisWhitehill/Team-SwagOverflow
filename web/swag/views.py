from rest_framework.viewsets import ModelViewSet
from swag.serializers import UserSerializer, TeamSerializer, ShowSerializer, FavoriteTeamSerializer, FavoriteShowSerializer, TeamEventSerializer, ShowEventSerializer
from swag.models import User, Team, Show, FavoriteTeam, FavoriteShow, TeamEvent, ShowEvent


class UserViewSet(ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer


class TeamViewSet(ModelViewSet):
    queryset = Team.objects.all()
    serializer_class = TeamSerializer


class ShowViewSet(ModelViewSet):
    queryset = Show.objects.all()
    serializer_class = ShowSerializer


class FavoriteTeamViewSet(ModelViewSet):
    queryset = FavoriteTeam.objects.all()
    serializer_class = FavoriteTeamSerializer


class FavoriteShowViewSet(ModelViewSet):
    queryset = FavoriteShow.objects.all()
    serializer_class = FavoriteShowSerializer


class TeamEventViewSet(ModelViewSet):
    queryset = TeamEvent.objects.all()
    serializer_class = TeamEventSerializer


class ShowEventViewSet(ModelViewSet):
    queryset = ShowEvent.objects.all()
    serializer_class = ShowEventSerializer
