import pandas as pd
import pymssql

lg = []
ss = []

conn = pymssql.connect(host=r"(local)", database='mypy', charset='utf8')
cursor = conn.cursor()

cursor.execute("SELECT top 10 s_price FROM stock where s_name='LG'")
row = cursor.fetchone()
while row :
    print(row[0])
    lg.append(row[0])
    row = cursor.fetchone()
    
cursor.execute("SELECT top 10 s_price FROM stock where s_name='삼성전자'")
row = cursor.fetchone()
while row :
    print(row[0])
    ss.append(row[0])
    row = cursor.fetchone()    
    
conn.close()   


raw_data = {    'lg' : lg,
                'ss' : ss} 
raw_data = pd.DataFrame(raw_data) 
raw_data.to_excel(excel_writer='stock.xlsx', sheet_name='증권', index=False, header=True) 