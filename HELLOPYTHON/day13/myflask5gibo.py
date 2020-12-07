from flask import Flask, render_template, request
import pymssql
import qrcode

app = Flask(__name__)

@app.route("/gibo")
def gugu():
    
    my_list = []
    #db 접근 group by
    conn = pymssql.connect(host='localhost', user="sa", password="java", database="mypy")
    cursor = conn.cursor()
    
    cursor.execute("SELECT pan from omokgibo group by pan order by pan desc")
    row = cursor.fetchone()
    while row:
        my_list.append(row[0])
        makeQr(str(row[0]))
        row = cursor.fetchone()
    
    conn.close()
    return render_template('gibo.html' , my_list=my_list)

@app.route("/gibodetail")
def gibodetail():
    pan = request.args.get("pan")
    my_list = []
    #db 접근 group by
    win = ""
    
    conn = pymssql.connect(host='localhost', user="sa", password="java", database="mypy")
    cursor = conn.cursor()
    
    cursor.execute("SELECT pan, seq,gibo,win from omokgibo where pan="+pan+" order by seq")
    row = cursor.fetchone()
    
    while row:
        my_list.append(row[2])
        win = row[3]
        row = cursor.fetchone()
    
    conn.close()
    #db 접근 order by
    return render_template('gibodetail.html',my_list=my_list, win=win)

def makeQr(pan):
    print("qrPan" +pan);
    qr = qrcode.QRCode(
    version = 2,
    error_correction = qrcode.constants.ERROR_CORRECT_H,
    box_size = 2,
    border = 2
    )
    url = 'http://192.168.45.63:7777/gibodetail?pan='+pan
    qr.add_data(url)
    qr.make()
    img = qr.make_image(fill_color="black", back_color="white")
    img.save('static/qr'+pan+'.png')

if __name__ == "__main__":
    app.run(host="192.168.45.63", port="7777")