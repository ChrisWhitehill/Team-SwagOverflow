# SwagOverflow Web Project

### URLS

These are the current available URLs. Detail views accept `GET`, `POST`, `PUT`, and `DELETE`. List views accept `GET`.

```
/api/favoriteshow/      swag.views.FavoriteShowViewSet  favoriteshow-list
/api/favoriteshow/<pk>/ swag.views.FavoriteShowViewSet  favoriteshow-detail 
/api/favoriteteam/      swag.views.FavoriteTeamViewSet  favoriteteam-list
/api/favoriteteam/<pk>/ swag.views.FavoriteTeamViewSet  favoriteteam-detail 
/api/show/              swag.views.ShowViewSet          show-list
/api/show/<pk>/         swag.views.ShowViewSet          show-detail 
/api/showevent/         swag.views.ShowEventViewSet     showevent-list
/api/showevent/<pk>/    swag.views.ShowEventViewSet     showevent-detail
/api/team/              swag.views.TeamViewSet          team-list
/api/team/<pk>/         swag.views.TeamViewSet          team-detail 
/api/teamevent/         swag.views.TeamEventViewSet     teamevent-list
/api/teamevent/<pk>/    swag.views.TeamEventViewSet     teamevent-detail
/api/user/              swag.views.UserViewSet          user-list
/api/user/<pk>/         swag.views.UserViewSet          user-detail
```
