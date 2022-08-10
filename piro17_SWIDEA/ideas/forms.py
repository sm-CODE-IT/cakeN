from django import forms 
from .models import Devtool, Idea

class DevtoolForm(forms.ModelForm):
    class Meta:
        model = Devtool
        fields = ('__all__')

class IdeaForm(forms.ModelForm):
    class Meta:
        model = Idea
        fields = ('__all__')
