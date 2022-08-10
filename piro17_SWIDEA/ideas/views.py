import re
from urllib import request
from django.shortcuts import render, get_object_or_404, redirect
from .models import Idea, Devtool, IdeaStar
from .forms import IdeaForm, DevtoolForm
import json
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.core.paginator import Paginator



## idea

def idea_list(request):
    ideas = Idea.objects.all()

    # 정렬하기
    sort_by = request.GET.get('sort', 'star')
    if sort_by == 'title':
        ideas = ideas.order_by('title', '-id')
    elif sort_by == 'star':
        dict = {}
        for idea in ideas:
            print(idea.idea_star.filter(idea=idea))

            dict[idea] = idea.idea_star.filter(idea=idea).count()
            print(dict[idea])
        ideas = sorted(ideas, key=lambda idea: dict[idea], reverse=True)
    elif sort_by == 'create':
        ideas = ideas.order_by('created_at')
    elif sort_by == 'recent':
        ideas = ideas.order_by('-created_at')

    # 페이징 처리
    page = request.GET.get('page', '1')
    paginator = Paginator(ideas, 4)
    page_obj = paginator.get_page(page)


    # 찜하기 기능
    ideas_star_dict = {}
    for idea in page_obj:
        if idea.idea_star.filter(idea=idea).count() > 0:
            ideas_star_dict[idea] = True
        else:
            ideas_star_dict[idea] = False

    ctx = {
        'ideas': page_obj,
        'sort_by': sort_by,
        'ideas_star_dict': ideas_star_dict
    }

    return render(request, template_name='ideas/idea_list.html', context=ctx)

def idea_detail(request, pk):
    ideas = get_object_or_404(Idea, pk=pk)
    idea = Idea.objects.get(id=pk)
    idea_star = IdeaStar.objects.filter(idea=idea)
    
    if idea_star:
        is_star = True
    else:
        is_star = False

    tool = Devtool.objects.get(name=idea.devtool)
    ctx = {'ideas': ideas, 'tools': tool, 'is_star': is_star}

    return render(request, template_name='ideas/idea_detail.html', context=ctx)

def idea_create(request):
    if request.method == 'POST':
        form = IdeaForm(request.POST)
        if form.is_valid():
            idea = form.save()
            return redirect('ideas:idea_detail', pk=idea.pk)

    else:
        form = IdeaForm()
        ctx = {'form':form}

        return render(request, template_name='ideas/idea_form.html', context=ctx)

def idea_update(request, pk):
    idea = get_object_or_404(Idea, pk=pk)

    if request.method == 'POST':
        form = IdeaForm(request.POST, instance=idea)

        image = request.FILES.get("image")
        idea.image = image
        idea.save()
        
        if form.is_valid():
            idea = form.save()

            return redirect('ideas:idea_detail', pk)
    else:
        form = IdeaForm(instance=idea)
        ctx = {'form':form}
        return render(request, template_name='ideas/idea_update.html', context=ctx)

def idea_delete(request, pk):
    ideas = get_object_or_404(Idea, pk=pk)
    ideas.delete()

    return redirect('ideas:idea_list')


## tool

def tool_list(request):
    tools = Devtool.objects.all()
    # 페이징 처리
    page = request.GET.get('page', '1')
    paginator = Paginator(tools, 7)
    page_obj = paginator.get_page(page)

    ctx = {'tools': page_obj}

    return render(request, template_name='ideas/tool_list.html', context=ctx)

def tool_detail(request, pk):
    tool = get_object_or_404(Devtool, pk=pk)
    idea = Idea.objects.filter(devtool=tool)
    ctx = {'tools': tool, 'ideas':idea}

    return render(request, template_name='ideas/tool_detail.html', context=ctx)

def tool_create(request):
    if request.method == 'POST':
        form = DevtoolForm(request.POST)
        if form.is_valid():
            tool = form.save()

            return redirect('ideas:tool_detail', pk=tool.pk)
    else:
        form = DevtoolForm()
        ctx = {'form': form}
        
        return render(request, template_name='ideas/tool_form.html', context=ctx)

def tool_update(request, pk):
    tool = get_object_or_404(Devtool, pk=pk)
    if request.method == 'POST':
        form = DevtoolForm(request.POST, instance=tool)
        if form.is_valid():
            tool = form.save()
            return redirect('ideas:tool_detail', pk)
    else:
        form = DevtoolForm(instance=tool)
    
    ctx = {'form': form}
    return render(request, template_name='ideas/tool_update.html', context=ctx)

def tool_delete(request, pk):
    tool = get_object_or_404(Devtool, pk=pk)
    tool.delete()

    return redirect('ideas:tool_list')


## button
@csrf_exempt
def idea_ajax(request):
    req = json.loads(request.body)
    idea_id = req['ideaId']
    button_type = req['type']
    print("!!")
    idea = get_object_or_404(Idea, id=idea_id)

    if button_type == 'plus':
        idea.interest += 1
    else:
        idea.interest -= 1
    idea.save()

    return JsonResponse({'ideaId': idea_id, 'total_likes': idea.interest})

## Star Ajax
@csrf_exempt
def idea_star_ajax(request):
    req = json.loads(request.body)
    idea_id = req['ideaId']
    idea = get_object_or_404(Idea, id=idea_id)

    idea_star = IdeaStar.objects.filter(idea=idea)

    if idea_star:
        idea_star.delete()
        is_star = False
    else:
        IdeaStar.objects.create(idea=idea)
        is_star = True

    idea.save()
    is_stared = is_star

    return JsonResponse({ 'ideaId': idea_id, 'is_star': is_stared })
