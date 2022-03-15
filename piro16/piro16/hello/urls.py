from django.contrib import admin
from django.urls import path, include
from .views import *   #.은 같은 디렉토리에 있는 파일임을 명시

urlpatterns = [
    path('', hello, name="hello"),  #hello라는 명령이 들어오면 hello 앱 url 파일로 찾아가겠다!
]