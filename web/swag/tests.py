from django.core.urlresolvers import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from swag.models import FavoriteTeam, Team, User


class AccountTests(APITestCase):

    def test_get_team(self):
        team = Team.objects.create(name='Twins', league='MLB', logo_url='')
        url = reverse('teams-list')
        response = self.client.get(url, format='json')
        self.assertEqual(response.status_code, 200)
        self.assertEqual(response.data[0]['name'], team.name)
