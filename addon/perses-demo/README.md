# Demo mínimo de Perses con Docker

Este ejemplo levanta tres contenedores:

1. `metrics-app`: una app mínima en Python que expone métricas Prometheus.
2. `prometheus`: scrapea las métricas de la app y sus propias métricas.
3. `perses`: UI de dashboards.

## Levantar el entorno

```bash
docker compose up -d --build
```

## Generar tráfico para que haya datos

En otra terminal, ejecuta varias veces:

```bash
curl http://localhost:8000/
curl http://localhost:8000/buy
```

O bien:

```bash
for i in {1..30}; do
  curl -s http://localhost:8000/ > /dev/null
  curl -s http://localhost:8000/buy > /dev/null
  sleep 1
done
```

## Validar componentes

- App: `http://localhost:8000/metrics`
- Prometheus: `http://localhost:9090`
- Perses: `http://localhost:8080`

## Crear el dashboard en Perses

Una vez abierto Perses:

1. Crea un proyecto, por ejemplo `demo`.
2. Crea un datasource de tipo **Prometheus**.
3. Usa como URL del datasource: `http://prometheus:9090`
   - Nota: dentro de Docker Compose, Perses debe hablar con el nombre del servicio, no con `localhost`.
4. Crea un dashboard nuevo.
5. Agrega un panel de serie de tiempo con alguna de estas consultas PromQL:

### Panel 1 - Requests por endpoint

```promql
sum by (endpoint, status) (rate(demo_requests_total[1m]))
```

### Panel 2 - Temperatura ficticia

```promql
demo_temperature_celsius
```

### Panel 3 - Latencia p95

```promql
histogram_quantile(0.95, sum by (le, endpoint) (rate(demo_request_latency_seconds_bucket[1m])))
```

## Resumen

- Prometheus recolecta métricas.
- Perses consume Prometheus como datasource.
- El dashboard se arma visualmente en minutos.
- Luego puedes evolucionar a "dashboard as code" para versionarlo.

## Limpieza

```bash
docker compose down
```
