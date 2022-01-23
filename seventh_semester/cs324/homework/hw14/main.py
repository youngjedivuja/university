from flask import Flask, render_template

main = Flask(__name__)


@main.route("/")
def home():
    return render_template("home.html")


@main.route('/about')
def about():
    return render_template("about.html")


@main.route('/contact')
def contact():
    return render_template("contact.html")


if __name__ == '__main__':
    main.run()
