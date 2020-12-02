import os
import sys
import urllib.request
from bs4 import BeautifulSoup
import pymssql
client_id = "uhsrtwWS0a0RiRLi8nOi"
client_secret = "s9n5LfaM_k"
encText = urllib.parse.quote("미션임파서블")
url = "https://openapi.naver.com/v1/search/movie.xml?query=" + encText # json 결과
request = urllib.request.Request(url)
request.add_header("X-Naver-Client-Id",client_id)
request.add_header("X-Naver-Client-Secret",client_secret)
request.add_header("Content-Type", "text/xml;charset=utf-8")
response = urllib.request.urlopen(request)
rescode = response.getcode()
if(rescode==200):
    response_body = response.read()
    soup = BeautifulSoup(response_body.decode('utf-8'), 'xml')
    items = soup.select("item")
    for item in items:
        title = item.title.text
        link = item.link.text
        image = item.image.text
        subtitle = item.subtitle.text
        pubDate = item.pubDate.text
        director = item.director.text
        actor = item.actor.text
        userRating = item.userRating.text
        
        print(title)
        print(link)
        print(subtitle)
        print(director)
        print(actor)
        print(userRating)
        
        sql = ""
        sql += "INSERT INTO MOVIE (title, link, pubDate, director, actor, userRating) VALUES "
        sql += "(%s,%s,%s,%s,%s,%s)"
        
        conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
        cursor = conn.cursor()
        cursor.execute(sql,(title,link,pubDate,director,actor,userRating))
else:
    print("Error Code:" + rescode)

# conn.commit()
# conn.close()