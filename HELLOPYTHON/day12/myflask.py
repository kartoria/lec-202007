from flask import Flask, request

app = Flask(__name__)


@app.route('/mypost', methods=['Post'])
def myget():
    num = request.form["num"]
    name = request.form["name"]
    
    out = ""
    out += "num : " + num + "<br/>"
    out += "name : " + name + "<br/>"
    
    return out

if __name__=="__main__":
    app.run(host="127.0.0.1", port="6974")