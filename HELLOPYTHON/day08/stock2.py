import pymssql
import requests
from bs4 import BeautifulSoup
import time

URL = "https://vip.mk.co.kr/newSt/rate/item_all.php"
result = requests.get(URL)

conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
cursor = conn.cursor()

soup = BeautifulSoup(result.content.decode('euc-kr'), 'html.parser')
findCompany = soup.select(".st2")
findValue = soup.select(".st2 + td")
print(len(findCompany), len(findValue))
if(len(findCompany) == len(findValue)):
    for i in range(0, len(findCompany)):
        company = findCompany[i].get_text()
        value = findValue[i].get_text()
        value = value.replace(",", "")
        cursor.execute("INSERT INTO STOCK (s_name, s_price, s_date) VALUES ('"+company+"', "+value+", getdate())")
        conn.commit()
        print(company, value)
        print("insert : ", cursor.rowcount)
    conn.close()







# while True:
#     obj = BeautifulSoup(result.content, "html.parser")
#     value = obj.find("strong", {"id" : "_nowVal"}).text
#     cursor.execute("INSERT INTO STOCK (stk_company, stk_value, stk_date) VALUES ('삼성전자', "+value+", getdate())")
#     print("value : ", value)


