from matplotlib import pyplot as plt
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


x = range(10)
print(ss)
print(lg)
 
plt.plot(x,lg)
plt.plot(x,ss)
plt.show()



 
