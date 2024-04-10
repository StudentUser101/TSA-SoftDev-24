from flask import Flask, request, jsonify
from flask_socketio import SocketIO 
from RAG_model import get_answer
app = Flask("api")


@app.route('/', methods=['POST','GET'])
def newchat():  
    if request.method == 'GET':
        return 'This is a GET request for AI-chat use a POST request', 200
    if request.method == 'POST':
        print(request.form)
        recipent =  request.form['recipent']
        tone =  request.form['tone']
        setting = request.form['setting']
        message = request.form['message']
        answer = get_answer(f"Help me communicate by the '{message}' with the tone: '{tone}',  the setting is a {setting},  The recipient is my {recipent},try: adding tips for handling conversations like this and rephrasing the question.")
        parsed_srting = answer.replace("\n","<br>")
        parsed_srting = parsed_srting.replace("\"","'")
        parsed_srting = parsed_srting.replace("\u2014","-")
        parsed_srting = parsed_srting.replace("\u2013","-")
        print(parsed_srting)
        return(jsonify({'answer':parsed_srting}))



if __name__ == "__main__":
    app.run(debug=True, port = 1800)