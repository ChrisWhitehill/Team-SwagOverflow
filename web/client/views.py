from client.decorators import template
from swag.models import Team, Show


@template('index.html')
def index(request, context):
    context['shows'] = Show.objects.all()
    context['teams'] = Team.objects.all()
