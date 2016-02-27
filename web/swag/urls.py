from django.conf.urls import include, url
from rest_framework_nested.routers import SimpleRouter, NestedSimpleRouter
from swag import views


router = SimpleRouter()

router.register(r'user', views.UserViewSet, base_name='users')

user_shows_router = NestedSimpleRouter(router, r'user', lookup='user')
user_shows_router.register(r'shows', views.FavoriteShowViewSet, base_name='user-shows')

user_teams_router = NestedSimpleRouter(router, r'user', lookup='user')
user_teams_router.register(r'teams', views.FavoriteTeamViewSet, base_name='user-teams')

router.register(r'team', views.TeamViewSet, base_name='teams')
router.register(r'show', views.ShowViewSet, base_name='shows')
router.register(r'teamevent', views.TeamEventViewSet, base_name='teamevents')
router.register(r'showevent', views.ShowEventViewSet, base_name='showevents')

urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^', include(user_shows_router.urls)),
    url(r'^', include(user_teams_router.urls)),
    url(r'^leagues', views.league_list),
]
