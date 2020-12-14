#-*- coding:utf-8 -*-
import urllib3
import json
import base64
from sympy.integrals.risch import recognize_derivative
openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/Recognition"
accessKey = "7571b980-5051-443c-ac08-1fba0b31c0df"
audioFilePath = "ourhouse.wav"
languageCode = "korean"

file = open(audioFilePath, "rb")
audioContents = base64.b64encode(file.read()).decode("utf8")
file.close()

requestJson = {
    "access_key": accessKey,
    "argument": {
        "language_code": languageCode,
        "audio": audioContents
    }
}

http = urllib3.PoolManager()
response = http.request(
    "POST",
    openApiURL,
    headers={"Content-Type": "application/json; charset=utf8"},
    body=json.dumps(requestJson)
)

print(response.data)
print(str(response.data,"utf-8"));
print("[responseCode] " + str(response.status))
print("[responBody]")
print()
print("json 파싱 이후:")
jsonObject = json.loads(response.data)
print(jsonObject.get("return_object").get("recognized"))