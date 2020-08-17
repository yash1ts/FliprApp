
# A very simple Flask Hello World app for you to get started with...

from flask import Flask,json
from datetime import datetime
from sqlalchemy import Column, Integer, Float, Date,Table,MetaData
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.sql import select,text

app = Flask(__name__)

engine = create_engine('sqlite:///app.db')
metadata = MetaData()

metadata.create_all(engine)
users = Table('BSE', metadata,
Column('id',Integer, primary_key=True, nullable=False),
Column('date',Date),
Column('opn',Float),
Column('hi',Float),
Column('lo',Float),
Column('close',Float),
Column('vol',Float))

conn = engine.connect()

@app.route('/')
def hello_world():
    s = text("SELECT max(hi),max(lo) FROM (SELECT hi,lo from BSE LIMIT 100)")
    s2= text("SELECT opn,close FROM BSE LIMIT 1")
    res2=conn.execute(s2)
    res = conn.execute(s)
    a=res.fetchone()
    b=res2.fetchone()
    data = {
        'hi'  : a[0],
        'lo' : a[1],
        'open':b[0],
        'close':b[1]
    }
    js = json.dumps(data)
    return js

@app.route('/graph')
def graph():
    s=text("SELECT date,close FROM BSE LIMIT 20")
    res=conn.execute(s)
    a={'result':[dict(row) for row in res]}
    return json.dumps(a)










