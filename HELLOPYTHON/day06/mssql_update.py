import pymssql
conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")

cursor = conn.cursor()

sql =  " UPDATE SAMPLE "
sql += " SET    COL01 = '업데이트'"
sql += ",       COL02 = '잘되냐?'" 
sql += " WHERE  COL01 = 'hihihih'"

cursor.execute(sql)

conn.commit()
conn.close()