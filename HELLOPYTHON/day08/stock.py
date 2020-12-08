import pymssql
import requests
from bs4 import BeautifulSoup
import time

URL = "https://finance.naver.com/item/sise.nhn?code=066570"

conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
cursor = conn.cursor()

while True:
    result = requests.get(URL)
    obj = BeautifulSoup(result.content, "html.parser")
    value = obj.find("strong", {"id" : "_nowVal"}).text
    value = value.replace(",", "")
    cursor.execute("INSERT INTO STOCK (s_name, s_price, s_date) VALUES ('LG', " + value + ",getdate())")
    print("value : ", value)
    print("insert : ", cursor.rowcount)
    conn.commit()
    time.sleep(60)

conn.close()

