from django.urls import path
from . import views

app_name = 'ideas'

urlpatterns = [
    path('', view=views.idea_list, name='idea_list'),
    path('<int:pk>/', view=views.idea_detail, name='idea_detail'),
    path('create/', view=views.idea_create, name='idea_create'),
    path('<int:pk>/update/', view=views.idea_update, name='idea_update'),
    path('<int:pk>/delete/', view=views.idea_delete, name='idea_delete'),
    path('tool/', view=views.tool_list, name='tool_list'),
    path('tool_create/', view=views.tool_create, name='tool_create'),
    path('<int:pk>/tool_detail/', view=views.tool_detail, name='tool_detail'),
    path('<int:pk>/tool_update/', view=views.tool_update, name='tool_update'),
    path('<int:pk>/tool_delete/', view=views.tool_delete, name='tool_delete'),
    path('idea_ajax/', view=views.idea_ajax, name='idea_ajax'),
    path('idea_star_ajax/', view=views.idea_star_ajax, name='idea_star_ajax'),
]
