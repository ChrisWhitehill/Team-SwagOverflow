from rest_framework.routers import SimpleRouter
from swag import views


router = SimpleRouter()

router.register(r'user', views.UserViewSet)
router.register(r'team', views.TeamViewSet)
router.register(r'show', views.ShowViewSet)
router.register(r'favoriteteam', views.FavoriteTeamViewSet)
router.register(r'favoriteshow', views.FavoriteShowViewSet)
router.register(r'teamevent', views.TeamEventViewSet)
router.register(r'showevent', views.ShowEventViewSet)

urlpatterns = router.urls
