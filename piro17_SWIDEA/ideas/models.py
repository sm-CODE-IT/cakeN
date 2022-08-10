from random import choice
from django.db import models


class Devtool(models.Model):
    name = models.CharField(verbose_name='이름', max_length=30)
    kind = models.CharField(verbose_name='종류', max_length=30, default = '')
    content = models.TextField(verbose_name='개발툴 설명', max_length=125, default = '')
    def __str__(self):
        return self.name

class Idea(models.Model):
    title = models.CharField(verbose_name='아이디어명', max_length=50)
    image = models.ImageField(upload_to="image", null=True, blank=True)
    content = models.TextField(verbose_name='아이디어 설명', max_length=150)
    interest = models.IntegerField(verbose_name='아이디어 관심도', default=0)
    devtool = models.ForeignKey(Devtool, verbose_name='예상 개발툴', blank=True, on_delete=models.CASCADE)
    created_at = models.DateTimeField(verbose_name='등록일자', auto_now_add=True)

    def __str__(self):
        return self.title

class IdeaStar(models.Model):
    idea = models.ForeignKey(Idea, on_delete=models.CASCADE, related_name='idea_star')

    def __str__(self):
        return self.idea.title
