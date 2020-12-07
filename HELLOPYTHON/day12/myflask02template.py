from flask import Flask, request, render_template

app = Flask(__name__)

@app.route("/default")
def hello():
    return render_template('default.html', myname="김선준", mylist=["당근", "사과", "파인애플"])

if __name__=="__main__":
    app.run(host="127.0.0.1", port="6974")