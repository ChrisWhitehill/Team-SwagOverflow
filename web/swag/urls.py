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
game_router = NestedSimpleRouter(router, r'team', lookup='team')
game_router.register(r'games', views.GameViewSet, base_name='team-games')

router.register(r'show', views.ShowViewSet, base_name='shows')
episode_router = NestedSimpleRouter(router, r'show', lookup='show')
episode_router.register(r'episodes', views.EpisodeViewSet, base_name='show-episodes')

urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^', include(user_shows_router.urls)),
    url(r'^', include(user_teams_router.urls)),
    url(r'^', include(game_router.urls)),
    url(r'^', include(episode_router.urls)),
    url(r'^leagues', views.league_list),
    url(r'^league/(?P<key>.*)', views.league_teams),
    url(r'^user/(?P<user_pk>.*)/favorites', views.user_favorites),
    url(r'^user/(?P<user_pk>.*)/events', views.user_events),
]
