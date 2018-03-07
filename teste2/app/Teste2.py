import requests
import lxml
import operator
import re
import string
from flask import Flask, render_template, request, jsonify
import json
from collections import Counter
from bs4 import BeautifulSoup

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def index():
    errors = []
    results = {}
    if request.method == "POST":
        # get url that the user has entered
        try:
            url = request.form['url']
            r = requests.get(url)
            soup = BeautifulSoup(r.text, 'html.parser')
            word = request.form['word']
            word_list = re.split('\s+', soup.text)
            print(word_list.count(word))
            list = []
            results = {
                'occurrence': str((word_list.count(word))),
                'word': word
            }
            list.append(results)
            # texto = "O total de ocorrencias da palavra '" + word + "' é de : " + str((word_list.count(word)))  + "" + "asd"
            return jsonify(list)
        except:
            errors.append(
                "Unable to get URL. Please make sure it's valid and try again."
            )
    return render_template('index.html', errors=errors, results=results)


if __name__ == '__main__':
    app.run()
