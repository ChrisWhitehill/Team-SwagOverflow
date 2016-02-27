from django.shortcuts import get_object_or_404
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.viewsets import ViewSet, ModelViewSet
from swag.serializers import *
from swag.models import *
from swag.renderers import WrappedJSONRenderer


@api_view(['GET'])
def league_list(request):
    data = []
    for key, name in Team.LEAGUES:
        data.append({'key': key, 'name': name})
    return Response({"leagues": data})


@api_view(['GET'])
def league_teams(request, key=None):
    queryset = Team.objects.filter(league=key.upper())
    serializer = TeamSerializer(queryset, many=True)
    return Response({'teams': serializer.data})


@api_view(['GET'])
def user_favorites(request, user_pk=None):
    teams_query = FavoriteTeam.objects.filter(user=user_pk)
    shows_query = FavoriteShow.objects.filter(user=user_pk)
    teams = FavoriteTeamSerializer(teams_query, many=True)
    shows = FavoriteShowSerializer(shows_query, many=True)
    data = {'teams': teams.data, 'shows': shows.data}
    return Response(data)


class UserViewSet(ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    renderer_classes = [WrappedJSONRenderer]

class TeamViewSet(ModelViewSet):
    queryset = Team.objects.all()
    serializer_class = TeamSerializer
    renderer_classes = [WrappedJSONRenderer]


class ShowViewSet(ViewSet):
    queryset = Show.objects.all()
    serializer_class = ShowSerializer
    renderer_classes = [WrappedJSONRenderer]


class FavoriteTeamViewSet(ModelViewSet):
    queryset = FavoriteTeam.objects.all()
    serializer_class = FavoriteTeamSerializer
    renderer_classes = [WrappedJSONRenderer]


class FavoriteShowViewSet(ModelViewSet):
    queryset = FavoriteShow.objects.all()
    serializer_class = FavoriteShowSerializer
    renderer_classes = [WrappedJSONRenderer]


class GameViewSet(ModelViewSet):
    queryset = Game.objects.all()
    serializer_class = GameSerializer
    renderer_classes = [WrappedJSONRenderer]


class EpisodeViewSet(ModelViewSet):
    queryset = Episode.objects.all()
    serializer_class = EpisodeSerializer
    renderer_classes = [WrappedJSONRenderer]
