import pymssql
conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")

cursor = conn.cursor()
cursor.execute("INSERT INTO SAMPLE (COL01, COL02) VALUES ('hihihih', 'helll')")
conn.commit()
conn.close()
    