import pymssql
conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")

cursor = conn.cursor()

cursor.execute("SELECT * FROM SAMPLE")

row = cursor.fetchone()

while row:
    print("col01 : ", row[0] , "\t\tcol02 : ", row[1])
    row = cursor.fetchone()
conn.close()