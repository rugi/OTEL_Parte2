import random
import time
from flask import Flask, Response
from prometheus_client import Counter, Gauge, Histogram, generate_latest, CONTENT_TYPE_LATEST

app = Flask(__name__)

REQUESTS_TOTAL = Counter(
    'demo_requests_total',
    'Total de solicitudes simuladas',
    ['endpoint', 'status']
)

TEMPERATURE = Gauge(
    'demo_temperature_celsius',
    'Temperatura ficticia para demo'
)

LATENCY = Histogram(
    'demo_request_latency_seconds',
    'Latencia simulada por endpoint',
    ['endpoint']
)

@app.route('/')
def home():
    endpoint = '/'
    status = random.choice(['200', '200', '200', '500'])
    latency = random.uniform(0.05, 0.8)
    REQUESTS_TOTAL.labels(endpoint=endpoint, status=status).inc()
    TEMPERATURE.set(round(random.uniform(20.0, 34.0), 2))
    with LATENCY.labels(endpoint=endpoint).time():
        time.sleep(latency)
    return {
        'ok': status == '200',
        'status': status,
        'latency_seconds': round(latency, 3)
    }, int(status)

@app.route('/buy')
def buy():
    endpoint = '/buy'
    status = random.choice(['200', '200', '201', '500'])
    latency = random.uniform(0.1, 1.2)
    REQUESTS_TOTAL.labels(endpoint=endpoint, status=status).inc()
    TEMPERATURE.set(round(random.uniform(20.0, 34.0), 2))
    with LATENCY.labels(endpoint=endpoint).time():
        time.sleep(latency)
    return {
        'ok': status in ['200', '201'],
        'status': status,
        'latency_seconds': round(latency, 3)
    }, int(status)

@app.route('/metrics')
def metrics():
    return Response(generate_latest(), mimetype=CONTENT_TYPE_LATEST)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000)
