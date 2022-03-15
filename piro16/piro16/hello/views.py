from django.shortcuts import render

# Create your views here.
def hello(request):

    context = {
        "name": "박예준",
    }

    return render(request, template_name="hello.html", context=context)