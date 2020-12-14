import json
import requests
from selenium import webdriver
from bs4 import BeautifulSoup

driver = webdriver.Chrome('chromedriver.exe')

data = open("tts4.m4a", "rb") # STT를 진행하고자 하는 음성 파일

Lang = "Kor" # Kor / Jpn / Chn / Eng
URL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + Lang
    
ID = # 인증 정보의 Client ID
Secret = # 인증 정보의 Client Secret
    
headers = {
    "Content-Type": "application/octet-stream", # Fix
    "X-NCP-APIGW-API-KEY-ID": ID,
    "X-NCP-APIGW-API-KEY": Secret,
}
response = requests.post(URL,  data=data, headers=headers)
rescode = response.status_code

indextest = []

if(rescode == 200):
    print (response.text)
    resp = response.text
    target = '"'
    index = -1
    while True:
        index = resp.find(target, index + 1)
        if index == -1:
            break
        #print('start=%d' % index)
        indextest.append(index)
    final = resp[indextest[2]+1:indextest[3]]
    driver.get('https://search.naver.com/search.naver?where=nexearch&sm=tab_jum&query='+final)
    html = driver.page_source
    soup = BeautifulSoup(html, 'html.parser')
    myitems = soup.select("#_nowVal")
else:
    print("Error : " + response.text)