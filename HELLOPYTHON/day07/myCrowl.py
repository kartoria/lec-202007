import requests
from bs4 import BeautifulSoup


params = {'myname' : '남도일', 'myphone' : '010-2018-0766'}
url = "http://localhost:8090/HELLOWEB/ServletTest"
req = requests.get(url,params=params)
soup = BeautifulSoup(req.text, 'html.parser')

tr = soup.select('tr')

for i in tr :
    td = i.select("td")
    print("이름 : ", td[0].text, "\t전화번호 : ", td[1].text)

