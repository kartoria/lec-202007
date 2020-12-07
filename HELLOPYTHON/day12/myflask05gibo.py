from flask import Flask, request, render_template
import pymssql

app = Flask(__name__)
@app.route("/gibo")
def gibo():
    conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
    cursor = conn.cursor()
    sql = "select pan from omokGibo group by pan order by pan desc"
    pan = []
    cursor.execute(sql)
    row = cursor.fetchone()
    while row:
        pan.append(row[0])
        row = cursor.fetchone()
    print(pan)
    conn.close()
    return render_template('gibo.html', pan=pan)

@app.route("/gibodetail")
def gibodetail():
    conn = pymssql.connect(host='127.0.0.1', user='sa', password='java', database='mypy', charset="utf8")
    cursor = conn.cursor()
    sql = "select seq,gibo,win from omokGibo where pan=1 order by seq"
    giboList = [];
    cursor.execute(sql)
    row = cursor.fetchone()
    while row:
        tmpList = []
        seq = str(row[0])
        gibo = str(row[1])
        tmpList.append(seq)
        tmpList.append(gibo)
        giboList.append(tmpList)
        row = cursor.fetchone()
    print(len(giboList))
    conn.close()
    return render_template('gibodetail.html', giboList=giboList)

if __name__=="__main__":
    app.run(host="127.0.0.1", port="6974")