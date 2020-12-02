import pymssql
from xml.etree.ElementTree import parse
from more_itertools.more import substrings


tree = parse('navermovie.xml')
root = tree.getroot().find("channel")

item = root.findall("item")

title = [x.findtext("title") for x in item]
link = [x.findtext("link") for x in item]
pubDate = [x.findtext("pubDate") for x in item]
director = [x.findtext("director") for x in item]
actor = [x.findtext("actor") for x in item]
userRating = [x.findtext("userRating") for x in item]


 
conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
cursor = conn.cursor()

sql = "INSERT INTO MOVIE (title, link, pubDate, director, actor, userRating) VALUES "
for i in range(0, len(item)) :
    sql += "('"+title[i]+"', '"+link[i]+"', '"+pubDate[i]+"', '"+director[i]+"', '"+actor[i]+"', '"+userRating[i]+"'),"
    
sql = sql[0:len(sql)-1]
print(sql)

cursor.execute(sql)
conn.commit()
conn.close()
