from django.db import models


class User(models.Model):
    name = models.CharField(max_length=128)
    email = models.CharField(max_length=128)
    phone = models.CharField(max_length=16)

    def __str__(self):
        return self.name


class Team(models.Model):
    LEAGUES = [
        ('MLB', 'Major League Baseball'),
        ('NBA', 'National Basketball Association'),
        ('NFL', 'National Football League'),
        ('NHL', 'National Hockey League'),
    ]
    name = models.CharField(max_length=128)
    league = models.CharField(max_length=8, choices=LEAGUES)
    logo_url = models.CharField(max_length=128)

    def __str__(self):
        return self.name


class Show(models.Model):
    name = models.CharField(max_length=128)
    logo_url = models.CharField(max_length=128, default="")

    def __str__(self):
        return self.name


class FavoriteTeam(models.Model):
    user = models.ForeignKey(User)
    team = models.ForeignKey(Team)
    notifications = models.BooleanField(default=True)

    def __str__(self):
        return str(self.user) + ' | ' + str(self.team)


class FavoriteShow(models.Model):
    user = models.ForeignKey(User)
    show = models.ForeignKey(Show)
    notifications = models.BooleanField(default=True)

    def __str__(self):
        return str(self.user) + ' | ' + str(self.show)


class Event(models.Model):
    date = models.DateTimeField()
    channel_name = models.CharField(max_length=32)
    channel_number = models.IntegerField()

    class Meta:
        abstract = True


class Game(Event):
    away_team = models.ForeignKey(Team, related_name='away_games')
    home_team = models.ForeignKey(Team, related_name='home_games')

    def __str__(self):
        return '{0} vs. {1}'.format(self.away_team, self.home_team)


class Episode(Event):
    show = models.ForeignKey(Show, related_name='events')

    def __str__(self):
        return '{0} at {1}'.format(self.show, self.date)
