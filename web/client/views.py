from client.decorators import template
from swag.models import Team, Show


@template('index.html')
def index(request, context):
    context['shows'] = Show.objects.all()
    context['teams'] = Team.objects.all()


@template('shows.html')
def shows(request,context):
    context['shows'] = Show.objects.all()


@template('teams.html')
def teams(request,context):
    context['teams'] = Team.objects.all()
