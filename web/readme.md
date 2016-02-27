# SwagOverflow Web Project

### URLS

These are the current available URLs. Detail views accept `GET`, `POST`, `PUT`, and `DELETE`. List views accept `GET`.

```
/api/league/<key>                   rest_framework.decorators.league_teams
/api/leagues                        rest_framework.decorators.league_list
/api/show/                          swag.views.ShowViewSet          shows-list
/api/show/<pk>/                     swag.views.ShowViewSet          shows-detail
/api/show/<show_pk>/episodes/       swag.views.EpisodeViewSet       show-episodes-list
/api/show/<show_pk>/episodes/<pk>/  swag.views.EpisodeViewSet       show-episodes-detail
/api/team/                          swag.views.TeamViewSet          teams-list
/api/team/<pk>/                     swag.views.TeamViewSet          teams-detail
/api/team/<team_pk>/games/          swag.views.GameViewSet          team-games-list 
/api/team/<team_pk>/games/<pk>/     swag.views.GameViewSet          team-games-detail
/api/user/                          swag.views.UserViewSet          users-list
/api/user/<pk>/                     swag.views.UserViewSet          users-detail
/api/user/<user_pk>/favorites       rest_framework.decorators.user_favorites
/api/user/<user_pk>/shows/          swag.views.FavoriteShowViewSet  user-shows-list 
/api/user/<user_pk>/shows/<pk>/     swag.views.FavoriteShowViewSet  user-shows-detail
/api/user/<user_pk>/teams/          swag.views.FavoriteTeamViewSet  user-teams-list
/api/user/<user_pk>/teams/<pk>/     swag.views.FavoriteTeamViewSet  user-teams-detail
```
