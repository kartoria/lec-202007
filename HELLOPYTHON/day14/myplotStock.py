import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np
import matplotlib.pyplot as plt
import random
import pymssql
# make 3d axes

conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="UTF-8")
cursor = conn.cursor()

def getArrBySPrice(cursor, snameArr):
    z = []
    for sname in snameArr:
        sql =  "SELECT top(10) s_name, s_price "
        sql += "FROM stock where s_name='" + sname +"' "
        sql += "order by s_date"
        cursor.execute(sql)
        row = cursor.fetchone()
        
        price_init = 0
        seq = 0
        s_price = []
        while row:
            if seq == 0:
                price_init = row[1]
            print(row[1])
            s_price.append((row[1]/price_init)*100)
            seq += 1
            row = cursor.fetchone()
        z.append(s_price)
    return z

def getArrBySName(cursor):
    snameArr = []
    sql = "select s_name from stock order by s_name, s_date"
    cursor.execute(sql)
    row = cursor.fetchone()
    while row:
        snameArr.append(row[0])
        row = cursor.fetchone()
    return snameArr   

fig = plt.figure()
ax = fig.gca(projection='3d')

snameArr = getArrBySName(cursor) 
z = getArrBySPrice(cursor, snameArr)
conn.close()


for j in range(len(z)): # 회사개수만큼
    x = []
    y = []
    for i in range(len(z[0])): # 해당 회사의 주가 개수만큼
        x.append(j)
        y.append(i*100)
    ax.plot(np.array(x), np.array(y), z[j])
 
# make labels
ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')
 
plt.show()


