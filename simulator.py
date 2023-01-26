from flask import Flask
from flask import Response
from flask import request
import pika
import time
import _thread
import requests

app = Flask(__name__)

@app.post("/start")
def start():
    json = request.get_json(force=True)
    freq = int(json['frequency'])
    coordinates = get_points(json['startLocation'], json['endLocation'])
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    channel.queue_declare(queue='test')
    _thread.start_new_thread(start_queuing, (coordinates, connection, freq))
    
    return Response(status=200)

def start_queuing(coordinates, connection, freq):
    for position in coordinates:
        print("position sent")
        position_str = str(position[0]) + ',' + str(position[1])
        connection.channel().basic_publish(exchange='', routing_key='test', body=position_str)
        time.sleep(freq)
    connection.close()

URL = 'https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf624847230978143c41d597c2dedd48e5737b&start={start}&end={end}'

def get_points(start, end):
    url = URL.format(start=array_to_string(start), end=array_to_string(end))
    r = requests.get(url)
    data = r.json()
    return data['features'][0]['geometry']['coordinates']

def array_to_string(array):
    return str(array[0]) + ',' + str(array[1])

if __name__ == '__main__':
    app.run(host='localhost', port=8085, debug=True)