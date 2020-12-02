import json
import requests

url = "https://dapi.kakao.com/v2/search/vclip"
query = {"query":"007"}
header = {'Authorization' : 'KakaoAK a31ff9fab0c9165913a2ee8eec36ded1'}
r = requests.get(url, headers=header, params=query)
jsonResult = json.loads(r.text)
for result in jsonResult['documents']:
    print("제목  : ", result['title'])
    print("url : ", result['url'])