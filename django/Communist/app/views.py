from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from .models import User
import hashlib

# Create your views here.

def login(request):
    if request.method == 'GET':
        if request.session.get('username') and request.session.get('password'):
            user = User.objects.get(username=request.session.get('username'))

            return render(request, "app/result.html", {"name": user.name, "gender": user.gender, "communist": user.communist})
        
        c_username = request.COOKIES.get('username')
        c_uid = request.COOKIES.get('uid')

        if c_uid and c_username:
            request.session['username'] = c_username
            request.session['uid'] = c_uid
            user = User.objects.get(username=request.session.get('username'))

            return render(request, "app/result.html", {"name": user.name, "gender": user.gender, "communist": user.communist})
        
        return render(request, "app/login.html")
    elif request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        try:
            user = User.objects.get(username=username)
        except Exception as e:
            print('sign error is', e)
            error_message = "用户名或密码错误"

            return render(request, "app/login.html", {"error_message": error_message})
        
        if password != user.password:
            error_message = "用户名或密码错误"
            
            return render(request, "app/login.html", {"error_message": error_message})
        
        request.session['username'] = username
        request.session['uid'] = user.id
        user = User.objects.get(username=request.session.get('username'))
        
        return render(request, "app/result.html", {"name": user.name, "gender": user.gender, "communist": user.communist})

