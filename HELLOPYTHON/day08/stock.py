import pymssql
import requests
from bs4 import BeautifulSoup
import time

URL = "https://finance.naver.com/item/sise.nhn?code=005930"
result = requests.get(URL)

conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
cursor = conn.cursor()

while True:
    obj = BeautifulSoup(result.content, "html.parser")
    value = obj.find("strong", {"id" : "_nowVal"}).text
    value = value.replace(",", "")
    cursor.execute("INSERT INTO STOCK (stk_company, stk_value, stk_date) VALUES ('삼성전자', "+value+", getdate())")
    print("value : ", value)
    print("insert : ", cursor.rowcount)
    conn.commit()
    time.sleep(60)

conn.close()

