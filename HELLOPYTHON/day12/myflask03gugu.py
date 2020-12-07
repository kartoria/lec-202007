from flask import Flask, request, render_template

app = Flask(__name__)
@app.route("/gugu.do")
def gugu():
    return render_template('gugudan.html')

@app.route("/gugudan", methods=['Post'])
def gugudan():
    num = int(request.form["num"])
    gugu = []
    for i in range(0,9):
        gugustr = str(num)+" * "+str(i)+" = "+ str(num*(i+1))
        gugu.append(gugustr)
    return render_template('gugudan.html', gugu=gugu)

if __name__=="__main__":
    app.run(host="127.0.0.1", port="6974")