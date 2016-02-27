from django.shortcuts import get_object_or_404
from rest_framework.viewsets import ViewSet
from rest_framework.response import Response
from swag.serializers import *
from swag.models import *


class UserViewSet(ViewSet):

    def list(self, request):
        queryset = User.objects.all()
        serializer = UserSerializer(queryset, many=True)
        return Response(serializer.data)

    def create(self, request):
        serializer = UserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = User.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = UserSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = User.objects.get(pk=pk)
        except User.DoesNotExist:
            return Response(status=404)
        serializer = UserSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
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
        return Response(serializer.data)

    def create(self, request):
        serializer = TeamSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = Team.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = TeamSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = Team.objects.get(pk=pk)
        except Team.DoesNotExist:
            return Response(status=404)
        serializer = TeamSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
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
        return Response(serializer.data)

    def create(self, request):
        serializer = ShowSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = Show.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = ShowSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = Show.objects.get(pk=pk)
        except Show.DoesNotExist:
            return Response(status=404)
        serializer = ShowSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
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
        teams = [f.team for f in queryset]
        serializer = TeamSerializer(teams, many=True)
        return Response(serializer.data)

    def create(self, request):
        serializer = FavoriteTeamSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None, domain_pk=None):
        queryset = FavoriteTeam.objects.filter(user=user_pk)
        item = get_object_or_404(queryset, pk=pk).team
        serializer = TeamSerializer(item)
        return Response(serializer.data)

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
        shows = [f.show for f in queryset]
        serializer = ShowSerializer(shows, many=True)
        return Response(serializer.data)

    def create(self, request):
        serializer = FavoriteShowSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None, user_pk=None):
        queryset = FavoriteShow.objects.filter(user=user_pk)
        item = get_object_or_404(queryset, pk=pk).show
        serializer = ShowSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = FavoriteShow.objects.get(pk=pk)
        except FavoriteShow.DoesNotExist:
            return Response(status=404)
        serializer = FavoriteShowSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
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
        return Response(serializer.data)

    def create(self, request):
        serializer = TeamEventSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = TeamEvent.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = TeamEventSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = TeamEvent.objects.get(pk=pk)
        except TeamEvent.DoesNotExist:
            return Response(status=404)
        serializer = TeamEventSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = TeamEvent.objects.get(pk=pk)
        except TeamEvent.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)


class ShowEventViewSet(ViewSet):

    def list(self, request):
        queryset = ShowEvent.objects.all()
        serializer = ShowEventSerializer(queryset, many=True)
        return Response(serializer.data)

    def create(self, request):
        serializer = ShowEventSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=201)
        return Response(serializer.errors, status=400)

    def retrieve(self, request, pk=None):
        queryset = ShowEvent.objects.all()
        item = get_object_or_404(queryset, pk=pk)
        serializer = ShowEventSerializer(item)
        return Response(serializer.data)

    def update(self, request, pk=None):
        try:
            item = ShowEvent.objects.get(pk=pk)
        except ShowEvent.DoesNotExist:
            return Response(status=404)
        serializer = ShowEventSerializer(item, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=400)

    def destroy(self, request, pk=None):
        try:
            item = ShowEvent.objects.get(pk=pk)
        except ShowEvent.DoesNotExist:
            return Response(status=404)
        item.delete()
        return Response(status=204)
