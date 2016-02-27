from django.shortcuts import get_object_or_404
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.viewsets import ViewSet, ModelViewSet
from swag.serializers import *
from swag.models import *
from swag.renderers import WrappedJSONRenderer
from twilio.rest import TwilioRestClient
from django.conf import settings


def send_sms(message, to):
    try:
        client = TwilioRestClient(settings.TWILIO_ACCOUNT_SID,
                                  settings.TWILIO_AUTH_TOKEN)
        client.messages.create(
            to_= to,
            from_ = settings.TWILIO_DEFAULT_CALLERID,
            body = message
        )
    except:
        pass


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


@api_view(['GET'])
def user_events(request, user_pk=None):
    favorites = FavoriteTeam.objects.filter(user=user_pk)
    teams = [f.team for f in favorites]
    games = [g for g in Game.objects.all()
             if g.away_team in teams
             or g.home_team in teams]
    _games = GameSerializer(games, many=True)

    favorites = FavoriteShow.objects.filter(user=user_pk)
    shows = [f.show for f in favorites]
    episodes = [e for e in Episode.objects.all() if e.show in shows]
    _episodes = EpisodeSerializer(episodes, many=True)

    data = {'games': _games.data, 'episodes': _episodes.data}
    return Response(data)


class UserViewSet(ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    renderer_classes = [WrappedJSONRenderer]

class TeamViewSet(ModelViewSet):
    queryset = Team.objects.all()
    serializer_class = TeamSerializer
    renderer_classes = [WrappedJSONRenderer]


class ShowViewSet(ModelViewSet):
    queryset = Show.objects.all()
    serializer_class = ShowSerializer
    renderer_classes = [WrappedJSONRenderer]


class FavoriteTeamViewSet(ModelViewSet):
    queryset = FavoriteTeam.objects.all()
    serializer_class = FavoriteTeamSerializer
    renderer_classes = [WrappedJSONRenderer]

    def list(self, request, user_pk=None):
        queryset = FavoriteTeam.objects.filter(user=user_pk)
        serializer = FavoriteTeamSerializer(queryset, many=True)
        return Response(serializer.data)

    def create(self, request, user_pk=None):
        serializer = FavoriteTeamPostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            team = FavoriteTeam.objects.get(pk=serializer.data['id'])
            user = User.objects.get(pk=request.data['user'])
            resp = FavoriteTeamSerializer(team)
            message = "Thanks for subscribing to the " + team.team.name + "!"
            send_sms(message, user.phone)
            return Response(resp.data, status=201)
        return Response(serializer.errors, status=400)


class FavoriteShowViewSet(ModelViewSet):
    queryset = FavoriteShow.objects.all()
    serializer_class = FavoriteShowSerializer
    renderer_classes = [WrappedJSONRenderer]

    def list(self, request, user_pk=None):
        queryset = FavoriteShow.objects.filter(user=user_pk)
        serializer = FavoriteShowSerializer(queryset, many=True)
        return Response(serializer.data)

    def create(self, request, user_pk=None):
        serializer = FavoriteShowPostSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            show = FavoriteShow.objects.get(pk=serializer.data['id'])
            user = User.objects.get(pk=request.data['user'])
            resp = FavoriteShowSerializer(show)
            episodes = show.show.episodes.all()
            message = "Thanks for subscribing to " + show.show.name + "!"
            send_sms(message, user.phone)
            return Response(resp.data, status=201)
        return Response(serializer.errors, status=400)



class GameViewSet(ModelViewSet):
    queryset = Game.objects.all()
    serializer_class = GameSerializer
    renderer_classes = [WrappedJSONRenderer]


class EpisodeViewSet(ModelViewSet):
    queryset = Episode.objects.all()
    serializer_class = EpisodeSerializer
    renderer_classes = [WrappedJSONRenderer]
