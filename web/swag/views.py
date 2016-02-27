from django.shortcuts import get_object_or_404
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.viewsets import ViewSet
from swag.serializers import *
from swag.models import *


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


class UserViewSet(ViewSet):

    def list(self, request):
        queryset = User.objects.all()
        serializer = UserSerializer(queryset, many=True)
        return Response({'users': serializer.data})

    def create(self, request):
        serializer = UserSerializer(data=request.data['user'])
        if serializer.is_valid():
            serializer.save()
            return Response({'user': serializer.data}, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = User.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = UserSerializer(item)
        return Response({'user': serializer.data})

    def update(self, request, pk=None):
        try:
            item = User.objects.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=404)
        serializer = UserSerializer(item, data=request.data['user'])
        if serializer.is_valid():
            serializer.save()
            return Response({'user': serializer.data})
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = User.objects.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class TeamViewSet(ViewSet):

    def list(self, request):
        queryset = Team.objects.all()
        serializer = TeamSerializer(queryset, many=True)
        return Response({'teams': serializer.data})

    def create(self, request):
        serializer = TeamSerializer(data=request.data['team'])
        if serializer.is_valid():
            serializer.save()
            return Response({'team': serializer.data}, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = Team.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = TeamSerializer(item)
        return Response({'team': serializer.data})

    def update(self, request, pk=None):
        try:
            item = Team.objects.get(pk=pk)
        except Team.DoesNotExist:
            return Response(status=404)
        serializer = TeamSerializer(item, data=request.data['team'])
        if serializer.is_valid():
            serializer.save()
            return Response({'team': serializer.data})
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = Team.objects.get(pk=pk)
        except Team.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class ShowViewSet(ViewSet):

    def list(self, request):
        queryset = Show.objects.all()
        serializer = ShowSerializer(queryset, many=True)
        return Response({'shows': serializer.data})

    def create(self, request):
        serializer = ShowSerializer(data=request.data['show'])
        if serializer.is_valid():
            serializer.save()
            return Response({'show': serializer.data}, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = Show.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = ShowSerializer(item)
        return Response({'show': serializer.data})

    def update(self, request, pk=None):
        try:
            item = Show.objects.get(pk=pk)
        except Show.DoesNotExist:
            return Response(status=404)
        serializer = ShowSerializer(item, data=request.data['show'])
        if serializer.is_valid():
            serializer.save()
            return Response({'show': serializer.data})
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = Show.objects.get(pk=pk)
        except Show.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class FavoriteTeamViewSet(ViewSet):

    def list(self, request, user_pk=None):
        queryset = FavoriteTeam.objects.filter(user=user_pk)
        serializer = FavoriteTeamSerializer(queryset, many=True)
        return Response({'favoriteteams': serializer.data})

    def create(self, request, user_pk=None):
        serializer = FavoriteTeamSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'favoriteteam': serializer.data}, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None, user_pk=None):
        queryset = FavoriteTeam.objects.filter(user=user_pk)
        item = get_object_or_404(queryset, pk=pk)
        serializer = FavoriteTeamSerializer(item)
        return Response({'favoriteteam': serializer.data})

    def update(self, request, pk=None):
        try:
            item = FavoriteTeam.objects.get(pk=pk)
        except FavoriteTeam.DoesNotExist:
            return Response(status=404)
        serializer = FavoriteTeamSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = FavoriteTeam.objects.get(pk=pk)
        except FavoriteTeam.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class FavoriteShowViewSet(ViewSet):

    def list(self, request, user_pk=None):
        queryset = FavoriteShow.objects.filter(user=user_pk)
        serializer = FavoriteShowSerializer(queryset, many=True)
        return Response({'favoriteshows': serializer.data})

    def create(self, request, user_pk=None):
        serializer = FavoriteShowSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None, user_pk=None):
        queryset = FavoriteShow.objects.filter(user=user_pk)
        item = get_object_or_404(queryset, pk=pk)
        serializer = FavoriteShowSerializer(item)
        return Response({'favoriteshow': serializer.data})

    def update(self, request, pk=None):
        try:
            item = FavoriteShow.objects.get(pk=pk)
        except FavoriteShow.DoesNotExist:
            return Response(status=404)
        serializer = FavoriteShowSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({'favoriteshow': serializer.data})
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = FavoriteShow.objects.get(pk=pk)
        except FavoriteShow.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class GameViewSet(ViewSet):

    def list(self, request):
        queryset = Game.objects.all()
        serializer = GameSerializer(queryset, many=True)
        return Response({'teamevents': serializer.data})

    def retrieve(self, request, pk=None):
        queryset = Game.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = GameSerializer(item)
        return Response({'teamevent': serializer.data})


class EpisodeViewSet(ViewSet):

    def list(self, request):
        queryset = Episode.objects.all()
        serializer = EpisodeSerializer(queryset, many=True)
        return Response({'showevents': serializer.data})

    def retrieve(self, request, pk=None):
        queryset = Episode.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = EpisodeSerializer(item)
        return Response({'showevent': serializer.data})
