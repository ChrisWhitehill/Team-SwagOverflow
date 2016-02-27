from rest_framework.renderers import JSONRenderer


class WrappedJSONRenderer(JSONRenderer):

    def render(self, data, accepted_media_type=None, renderer_context=None):
        response_data = {}
        serializer = renderer_context.get('view').get_serializer()
        resource = getattr(serializer.Meta, 'resource_name', 'objects')
        response_data[resource] = data
        response = super(WrappedJSONRenderer, self).render(response_data, accepted_media_type, renderer_context)
        return response
