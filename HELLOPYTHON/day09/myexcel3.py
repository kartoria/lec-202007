import pandas as pd 
import pymssql  
xlsx = pd.read_excel('stock.xlsx') 

arr_lg = xlsx['lg']
arr_ss = xlsx['ss']


conn = pymssql.connect(server='localhost', user='sa', password='java', database='mypy')  
cursor = conn.cursor()  


for item in arr_lg: 
    print(item)
    cursor.execute("INSERT excel (s_name,s_price) VALUES ('LG','"+str(item)+"')")  
    conn.commit()

for item in arr_ss: 
    print(item)
    cursor.execute("INSERT excel (s_name,s_price) VALUES ('SS','"+str(item)+"')")  
    conn.commit()


conn.close()