from django.shortcuts import get_object_or_404
from rest_framework.viewsets import ViewSet
from rest_framework.response import Response
from swag.serializers import *
from swag.models import *


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

    def create(self, request):
        serializer = FavoriteTeamSerializer(data=request.data['favoriteteam'])
        if serializer.is_valid():
            serializer.save()
            return Response({'favoriteteam': serializer.data}, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None, domain_pk=None):
        queryset = FavoriteTeam.objects.filter(user=user_pk)
        item = get_object_or_404(queryset, pk=pk)
        serializer = FavoriteTeamSerializer(item)
        return Response({'favoriteteam': serializer.data})

    def update(self, request, pk=None):
        try:
            item = FavoriteTeam.objects.get(pk=pk)
        except FavoriteTeam.DoesNotExist:
            return Response(status=404)
        serializer = FavoriteTeamSerializer(item, data=request.data['favoriteteam'])
        if serializer.is_valid():
            serializer.save()
            return Response({'favoriteteam': serializer.data})
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

    def create(self, request):
        serializer = FavoriteShowSerializer(data=request.data['favoriteshow'])
        if serializer.is_valid():
            serializer.save()
            return Response({'favoriteshow': serializer.data}, status=201)
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
        serializer = FavoriteShowSerializer(item, data=request.data['favoriteshow'])
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


class TeamEventViewSet(ViewSet):

    def list(self, request):
        queryset = TeamEvent.objects.all()
        serializer = TeamEventSerializer(queryset, many=True)
        return Response({'teamevents': serializer.data})

    def retrieve(self, request, pk=None):
        queryset = TeamEvent.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = TeamEventSerializer(item)
        return Response({'teamevent': serializer.data})


class ShowEventViewSet(ViewSet):

    def list(self, request):
        queryset = ShowEvent.objects.all()
        serializer = ShowEventSerializer(queryset, many=True)
        return Response({'showevents': serializer.data})

    def retrieve(self, request, pk=None):
        queryset = ShowEvent.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = ShowEventSerializer(item)
        return Response({'showevent': serializer.data})
