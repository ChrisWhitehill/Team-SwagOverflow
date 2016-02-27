# SwagOverflow Web Project

### URLS

These are the current available URLs. Detail views accept `GET`, `POST`, `PUT`, and `DELETE`. List views accept `GET`.

```
/api/show/                      swag.views.ShowViewSet          show-list
/api/show/<pk>/                 swag.views.ShowViewSet          show-detail 
/api/showevent/                 swag.views.ShowEventViewSet     showevent-list
/api/showevent/<pk>/            swag.views.ShowEventViewSet     showevent-detail
/api/team/                      swag.views.TeamViewSet          team-list
/api/team/<pk>/                 swag.views.TeamViewSet          team-detail 
/api/teamevent/                 swag.views.TeamEventViewSet     teamevent-list
/api/teamevent/<pk>/            swag.views.TeamEventViewSet     teamevent-detail
/api/user/                      swag.views.UserViewSet          user-list
/api/user/<pk>/                 swag.views.UserViewSet          user-detail
/api/user/<user_pk>/shows/      swag.views.FavoriteShowViewSet  user-shows-list
/api/user/<user_pk>/shows/<pk>/ swag.views.FavoriteShowViewSet  user-shows-detail
/api/user/<user_pk>/teams/      swag.views.FavoriteTeamViewSet  user-teams-list
/api/user/<user_pk>/teams/<pk>/ swag.views.FavoriteTeamViewSet  user-teams-detail
```