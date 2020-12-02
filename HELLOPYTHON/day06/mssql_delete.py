import pymssql
conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
cursor = conn.cursor()

sql =  " DELETE FROM SAMPLE "
sql += " WHERE  COL01 = '2'"

cursor.execute(sql)
conn.commit()
conn.close()