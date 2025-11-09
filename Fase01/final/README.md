# 🟢 Fase 1 – Captura de Trazas con OpenTelemetry

Este módulo representa la **primera fase** del laboratorio de observabilidad distribuida basado en OpenTelemetry.  
Aquí se construye un stack mínimo pero funcional que permite capturar, recolectar y visualizar trazas distribuidas generadas desde un microservicio Java.

---

## 🎯 Objetivo

> Demostrar cómo capturar trazas de una aplicación Java instrumentada automáticamente, enviarlas a un OpenTelemetry Collector y visualizarlas en Jaeger usando el protocolo Zipkin.

---

## 📦 Stack utilizado

| Componente           | Descripción                                     |
|----------------------|-------------------------------------------------|
| `app-java`           | Microservicio Spring Boot con agente OpenTelemetry Java |
| `otel-collector`     | OpenTelemetry Collector con receptor OTLP y exportador Zipkin |
| `jaeger`             | Visualizador de trazas                          |
| `docker-compose`     | Orquestación de los contenedores                |

---

## 🧱 Estructura

```
fase-1-tracing/
├── app-java/                  # Proyecto Java instrumentado (instrumentación automática)
├── otel-collector/            # Configuración del Collector
│   └── config.yaml
├── docker-compose.yml         # Orquestador de servicios
├── lab-operaciones.md         # Guía para levantar, detener y explorar el laboratorio
├── scripts/
│   └── generar_trafico.sh     # Script para generar trazas
└── README.md                  # Este documento
```

## Diagrama de componentes

Diagrama de componentes:
``` mermaid
graph TD
    A[💻 Linux Host] --> B[🐳 Docker Engine]
    B --> C[📦 Docker Compose]

    %% Contenedores encima del Compose
    subgraph Containers [Contenedores en ejecución]
        C --> D1[app-java Puerto: 8080]
        C --> D2[otel-collector Puertos: 4317, 55681, 13133, 8888]
        C --> D3[jaeger Puertos: 16686, 14250, 9411]
    end

    %% Conexiones de red internas
    D1 ---|OTLP gRPC| D2
    D2 ---|Zipkin Exporter| D3

    %% Red
    subgraph Network [🌐 observabilidad-net -bridge-]
        D1
        D2
        D3
    end

    %% Estilo visual
    style A fill:#2f3542,stroke:#fff,stroke-width:1px,color:#fff
    style B fill:#57606f,stroke:#fff,color:#fff
    style C fill:#70a1ff,stroke:#fff,color:#fff
    style D1 fill:#7bed9f,stroke:#2f3542
    style D2 fill:#fffa65,stroke:#2f3542
    style D3 fill:#ff6b81,stroke:#2f3542
    style Network fill:#dfe4ea,stroke:#57606f,stroke-dasharray:3 3
```

---

## 🚀 Puesta en marcha.
Vamos a abrir dos ventanas de linea de comandos, en la primera levantaremos nuestros contenedores usando docker compose, y en la otra ejecutaremos comandos adicionales.

En ambos casos, colocate en este misma carpeta.
`PATH_LOCAL/Fase01/final/`

### Ventana CLI 01
Ejecuta:

```console
%> docker compose up
```

Te aparecerá algo como lo siguiente:
```console
%> docker compose up
[+] Running 4/4
 ✔ Network final_observabilidad-net  Created                                                                                                                                              0.0s
 ✔ Container jaeger                  Created                                                                                                                                              0.1s
 ✔ Container otel-collector          Created                                                                                                                                              0.1s
 ✔ Container app-java                Created                                                                                                                                              0.1s
Attaching to app-java, jaeger, otel-collector
jaeger          | 2025/11/08 01:11:42 maxprocs: Leaving GOMAXPROCS=12: CPU quota undefined
jaeger          | 2025/11/08 01:11:42 application version: git-commit=2d351c3f30072cae7f5755be20e34c2697b9e3b5, git-version=v1.49.0, build-date=2023-09-07T13:13:08Z
jaeger          | {"level":"info","ts":1762564302.5749776,"caller":"flags/service.go:119","msg":"Mounting metrics handler on admin server","route":"/metrics"}
jaeger          | {"level":"info","ts":1762564302.5789778,"caller":"flags/service.go:125","msg":"Mounting expvar handler on admin server","route":"/debug/vars"}
jaeger          | {"level":"info","ts":1762564302.5797644,"caller":"flags/admin.go:129","msg":"Mounting health check on admin server","route":"/"}
jaeger          | {"level":"info","ts":1762564302.5798604,"caller":"flags/admin.go:143","msg":"Starting admin HTTP server","http-addr":":14269"}
jaeger          | {"level":"info","ts":1762564302.5798998,"caller":"flags/admin.go:121","msg":"Admin server started","http.host-port":"[::]:14269","health-status":"unavailable"}
jaeger          | {"level":"info","ts":1762564302.579991,"caller":"grpc@v1.57.0/clientconn.go:477","msg":"[core][Channel #1] Channel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5800357,"caller":"grpc@v1.57.0/clientconn.go:1786","msg":"[core][Channel #1] original dial target is: \"localhost:4317\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5800588,"caller":"grpc@v1.57.0/clientconn.go:1793","msg":"[core][Channel #1] parsed dial target is: {URL:{Scheme:localhost Opaque:4317 User: Host: Path: RawPath: OmitHost:false ForceQuery:false RawQuery: Fragment: RawFragment:}}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5800688,"caller":"grpc@v1.57.0/clientconn.go:1807","msg":"[core][Channel #1] fallback to scheme \"passthrough\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5800815,"caller":"grpc@v1.57.0/clientconn.go:1815","msg":"[core][Channel #1] parsed dial target is: {URL:{Scheme:passthrough Opaque: User: Host: Path:/localhost:4317 RawPath: OmitHost:false ForceQuery:false RawQuery: Fragment: RawFragment:}}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5800915,"caller":"grpc@v1.57.0/clientconn.go:1948","msg":"[core][Channel #1] Channel authority set to \"localhost:4317\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.582471,"caller":"grpc@v1.57.0/resolver_conn_wrapper.go:238","msg":"[core][Channel #1] Resolver state updated: {\n  \"Addresses\": [\n    {\n      \"Addr\": \"localhost:4317\",\n      \"ServerName\": \"\",\n      \"Attributes\": null,\n      \"BalancerAttributes\": null,\n      \"Type\": 0,\n      \"Metadata\": null\n    }\n  ],\n  \"ServiceConfig\": null,\n  \"Attributes\": null\n} (resolver returned new addresses)","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5825605,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:192","msg":"[core][Channel #1] Channel switches to new LB policy \"pick_first\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.582597,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:312","msg":"[core][Channel #1 SubChannel #2] Subchannel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5836358,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #1] Channel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5838115,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #1 SubChannel #2] Subchannel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5839393,"caller":"grpc@v1.57.0/clientconn.go:1414","msg":"[core][Channel #1 SubChannel #2] Subchannel picks a new address \"localhost:4317\" to connect","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.586863,"caller":"memory/factory.go:66","msg":"Memory storage initialized","configuration":{"MaxTraces":0}}
jaeger          | {"level":"info","ts":1762564302.587459,"caller":"static/strategy_store.go:138","msg":"Loading sampling strategies","filename":"/etc/jaeger/sampling_strategies.json"}
jaeger          | {"level":"warn","ts":1762564302.5875282,"caller":"grpc@v1.57.0/clientconn.go:1476","msg":"[core][Channel #1 SubChannel #2] grpc: addrConn.createTransport failed to connect to {Addr: \"localhost:4317\", ServerName: \"localhost:4317\", }. Err: connection error: desc = \"transport: Error while dialing: dial tcp 127.0.0.1:4317: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.587591,"caller":"grpc@v1.57.0/clientconn.go:1303","msg":"[core][Channel #1 SubChannel #2] Subchannel Connectivity change to TRANSIENT_FAILURE, last error: connection error: desc = \"transport: Error while dialing: dial tcp 127.0.0.1:4317: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.5876563,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #1] Channel Connectivity change to TRANSIENT_FAILURE","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6192315,"caller":"grpc@v1.57.0/server.go:652","msg":"[core][Server #3] Server created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.619361,"caller":"server/grpc.go:104","msg":"Starting jaeger-collector gRPC server","grpc.host-port":"[::]:14250"}
jaeger          | {"level":"info","ts":1762564302.6194198,"caller":"server/http.go:56","msg":"Starting jaeger-collector HTTP server","http host-port":":14268"}
jaeger          | {"level":"info","ts":1762564302.6194952,"caller":"grpc@v1.57.0/server.go:840","msg":"[core][Server #3 ListenSocket #4] ListenSocket created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6196613,"caller":"server/zipkin.go:56","msg":"Listening for Zipkin HTTP traffic","zipkin host-port":":9411"}
jaeger          | {"level":"warn","ts":1762564302.6393769,"caller":"internal@v0.84.0/warning.go:40","msg":"Using the 0.0.0.0 address exposes this server to every network interface, which may facilitate Denial of Service attacks","documentation":"https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks"}
jaeger          | {"level":"info","ts":1762564302.6394675,"caller":"grpc@v1.57.0/server.go:652","msg":"[core][Server #5] Server created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6394885,"caller":"otlpreceiver@v0.84.0/otlp.go:83","msg":"Starting GRPC server","endpoint":"0.0.0.0:4317"}
jaeger          | {"level":"warn","ts":1762564302.6396391,"caller":"internal@v0.84.0/warning.go:40","msg":"Using the 0.0.0.0 address exposes this server to every network interface, which may facilitate Denial of Service attacks","documentation":"https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks"}
jaeger          | {"level":"info","ts":1762564302.6396801,"caller":"otlpreceiver@v0.84.0/otlp.go:101","msg":"Starting HTTP server","endpoint":"0.0.0.0:4318"}
jaeger          | {"level":"info","ts":1762564302.6396902,"caller":"grpc@v1.57.0/server.go:840","msg":"[core][Server #5 ListenSocket #6] ListenSocket created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6397414,"caller":"grpc/builder.go:73","msg":"Agent requested insecure grpc connection to collector(s)"}
jaeger          | {"level":"info","ts":1762564302.6397803,"caller":"grpc@v1.57.0/clientconn.go:477","msg":"[core][Channel #7] Channel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6398025,"caller":"grpc@v1.57.0/clientconn.go:1786","msg":"[core][Channel #7] original dial target is: \":14250\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6398404,"caller":"grpc@v1.57.0/clientconn.go:1791","msg":"[core][Channel #7] dial target \":14250\" parse failed: parse \":14250\": missing protocol scheme","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6398706,"caller":"grpc@v1.57.0/clientconn.go:1807","msg":"[core][Channel #7] fallback to scheme \"passthrough\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.639895,"caller":"grpc@v1.57.0/clientconn.go:1815","msg":"[core][Channel #7] parsed dial target is: {URL:{Scheme:passthrough Opaque: User: Host: Path:/:14250 RawPath: OmitHost:false ForceQuery:false RawQuery: Fragment: RawFragment:}}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6399057,"caller":"grpc@v1.57.0/clientconn.go:1948","msg":"[core][Channel #7] Channel authority set to \"localhost:14250\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6399598,"caller":"grpc@v1.57.0/resolver_conn_wrapper.go:238","msg":"[core][Channel #7] Resolver state updated: {\n  \"Addresses\": [\n    {\n      \"Addr\": \":14250\",\n      \"ServerName\": \"\",\n      \"Attributes\": null,\n      \"BalancerAttributes\": null,\n      \"Type\": 0,\n      \"Metadata\": null\n    }\n  ],\n  \"ServiceConfig\": null,\n  \"Attributes\": null\n} (resolver returned new addresses)","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6400259,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:192","msg":"[core][Channel #7] Channel switches to new LB policy \"round_robin\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6407144,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:312","msg":"[core][Channel #7 SubChannel #8] Subchannel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6408243,"caller":"base/balancer.go:177","msg":"[roundrobin]roundrobinPicker: Build called with info: {map[]}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6408496,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #7] Channel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.641011,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #7 SubChannel #8] Subchannel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6410778,"caller":"grpc@v1.57.0/clientconn.go:1414","msg":"[core][Channel #7 SubChannel #8] Subchannel picks a new address \":14250\" to connect","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6411011,"caller":"grpc/builder.go:113","msg":"Checking connection to collector"}
jaeger          | {"level":"info","ts":1762564302.6411157,"caller":"grpc/builder.go:124","msg":"Agent collector connection state change","dialTarget":":14250","status":"CONNECTING"}
jaeger          | {"level":"info","ts":1762564302.6416817,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #7 SubChannel #8] Subchannel Connectivity change to READY","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6417458,"caller":"base/balancer.go:177","msg":"[roundrobin]roundrobinPicker: Build called with info: {map[SubConn(id:8):{{Addr: \":14250\", ServerName: \"\", }}]}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.641762,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #7] Channel Connectivity change to READY","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6417735,"caller":"grpc/builder.go:124","msg":"Agent collector connection state change","dialTarget":":14250","status":"READY"}
jaeger          | {"level":"info","ts":1762564302.6427245,"caller":"./main.go:259","msg":"Starting agent"}
jaeger          | {"level":"info","ts":1762564302.6437423,"caller":"querysvc/query_service.go:134","msg":"Archive storage not created","reason":"archive storage not supported"}
jaeger          | {"level":"info","ts":1762564302.6437721,"caller":"app/agent.go:69","msg":"Starting jaeger-agent HTTP server","http-port":5778}
jaeger          | {"level":"info","ts":1762564302.6437917,"caller":"app/flags.go:146","msg":"Archive storage not initialized"}
jaeger          | {"level":"info","ts":1762564302.6440642,"caller":"grpc@v1.57.0/server.go:652","msg":"[core][Server #11] Server created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.644151,"caller":"grpc@v1.57.0/clientconn.go:477","msg":"[core][Channel #12] Channel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6441693,"caller":"grpc@v1.57.0/clientconn.go:1786","msg":"[core][Channel #12] original dial target is: \":16685\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6442027,"caller":"grpc@v1.57.0/clientconn.go:1791","msg":"[core][Channel #12] dial target \":16685\" parse failed: parse \":16685\": missing protocol scheme","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.644214,"caller":"grpc@v1.57.0/clientconn.go:1807","msg":"[core][Channel #12] fallback to scheme \"passthrough\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6442556,"caller":"grpc@v1.57.0/clientconn.go:1815","msg":"[core][Channel #12] parsed dial target is: {URL:{Scheme:passthrough Opaque: User: Host: Path:/:16685 RawPath: OmitHost:false ForceQuery:false RawQuery: Fragment: RawFragment:}}","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.644269,"caller":"grpc@v1.57.0/clientconn.go:1948","msg":"[core][Channel #12] Channel authority set to \"localhost:16685\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6443365,"caller":"grpc@v1.57.0/resolver_conn_wrapper.go:238","msg":"[core][Channel #12] Resolver state updated: {\n  \"Addresses\": [\n    {\n      \"Addr\": \":16685\",\n      \"ServerName\": \"\",\n      \"Attributes\": null,\n      \"BalancerAttributes\": null,\n      \"Type\": 0,\n      \"Metadata\": null\n    }\n  ],\n  \"ServiceConfig\": null,\n  \"Attributes\": null\n} (resolver returned new addresses)","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6443977,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:192","msg":"[core][Channel #12] Channel switches to new LB policy \"pick_first\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.644445,"caller":"grpc@v1.57.0/balancer_conn_wrappers.go:312","msg":"[core][Channel #12 SubChannel #13] Subchannel created","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6445138,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #12] Channel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6446178,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #12 SubChannel #13] Subchannel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6450572,"caller":"grpc@v1.57.0/clientconn.go:1414","msg":"[core][Channel #12 SubChannel #13] Subchannel picks a new address \":16685\" to connect","system":"grpc","grpc_log":true}
jaeger          | {"level":"warn","ts":1762564302.6457603,"caller":"grpc@v1.57.0/clientconn.go:1476","msg":"[core][Channel #12 SubChannel #13] grpc: addrConn.createTransport failed to connect to {Addr: \":16685\", ServerName: \"localhost:16685\", }. Err: connection error: desc = \"transport: Error while dialing: dial tcp :16685: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.645831,"caller":"grpc@v1.57.0/clientconn.go:1303","msg":"[core][Channel #12 SubChannel #13] Subchannel Connectivity change to TRANSIENT_FAILURE, last error: connection error: desc = \"transport: Error while dialing: dial tcp :16685: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6458619,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #12] Channel Connectivity change to TRANSIENT_FAILURE","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564302.6463172,"caller":"app/server.go:215","msg":"Query server started","http_addr":"[::]:16686","grpc_addr":"[::]:16685"}
jaeger          | {"level":"info","ts":1762564302.6464033,"caller":"healthcheck/handler.go:129","msg":"Health Check state change","status":"ready"}
jaeger          | {"level":"info","ts":1762564302.6464236,"caller":"app/server.go:298","msg":"Starting GRPC server","port":16685,"addr":":16685"}
jaeger          | {"level":"info","ts":1762564302.6464362,"caller":"app/server.go:279","msg":"Starting HTTP server","port":16686,"addr":":16686"}
jaeger          | {"level":"info","ts":1762564302.6464438,"caller":"grpc@v1.57.0/server.go:840","msg":"[core][Server #11 ListenSocket #14] ListenSocket created","system":"grpc","grpc_log":true}
app-java        | OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
jaeger          | {"level":"info","ts":1762564303.5878162,"caller":"grpc@v1.57.0/clientconn.go:1303","msg":"[core][Channel #1 SubChannel #2] Subchannel Connectivity change to IDLE, last error: connection error: desc = \"transport: Error while dialing: dial tcp 127.0.0.1:4317: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.5878844,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #1 SubChannel #2] Subchannel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.5878975,"caller":"grpc@v1.57.0/clientconn.go:1414","msg":"[core][Channel #1 SubChannel #2] Subchannel picks a new address \"localhost:4317\" to connect","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.588498,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #1 SubChannel #2] Subchannel Connectivity change to READY","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.588559,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #1] Channel Connectivity change to READY","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.6459837,"caller":"grpc@v1.57.0/clientconn.go:1303","msg":"[core][Channel #12 SubChannel #13] Subchannel Connectivity change to IDLE, last error: connection error: desc = \"transport: Error while dialing: dial tcp :16685: connect: connection refused\"","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.6461139,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #12 SubChannel #13] Subchannel Connectivity change to CONNECTING","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.6461542,"caller":"grpc@v1.57.0/clientconn.go:1414","msg":"[core][Channel #12 SubChannel #13] Subchannel picks a new address \":16685\" to connect","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.6469338,"caller":"grpc@v1.57.0/clientconn.go:1301","msg":"[core][Channel #12 SubChannel #13] Subchannel Connectivity change to READY","system":"grpc","grpc_log":true}
jaeger          | {"level":"info","ts":1762564303.6470323,"caller":"grpc@v1.57.0/clientconn.go:565","msg":"[core][Channel #12] Channel Connectivity change to READY","system":"grpc","grpc_log":true}
app-java        | [otel.javaagent 2025-11-08 01:11:43:781 +0000] [main] INFO io.opentelemetry.javaagent.tooling.VersionLogger - opentelemetry-javaagent - version: 1.33.5
otel-collector  | 2025-11-08T01:11:43.842Z      info    service@v0.86.0/telemetry.go:84 Setting up own telemetry...
otel-collector  | 2025-11-08T01:11:43.844Z      info    service@v0.86.0/telemetry.go:201        Serving Prometheus metrics      {"address": ":8888", "level": "Basic"}
otel-collector  | 2025-11-08T01:11:43.852Z      info    service@v0.86.0/service.go:138  Starting otelcol-contrib...     {"Version": "0.86.0", "NumCPU": 12}
otel-collector  | 2025-11-08T01:11:43.852Z      info    extensions/extensions.go:31     Starting extensions...
otel-collector  | 2025-11-08T01:11:43.853Z      info    extensions/extensions.go:34     Extension is starting...        {"kind": "extension", "name": "health_check"}
otel-collector  | 2025-11-08T01:11:43.853Z      info    healthcheckextension@v0.86.0/healthcheckextension.go:35 Starting health_check extension {"kind": "extension", "name": "health_check", "config": {"Endpoint":"0.0.0.0:13133","TLSSetting":null,"CORS":null,"Auth":null,"MaxRequestBodySize":0,"IncludeMetadata":false,"ResponseHeaders":null,"Path":"/","ResponseBody":null,"CheckCollectorPipeline":{"Enabled":false,"Interval":"5m","ExporterFailureThreshold":5}}}
otel-collector  | 2025-11-08T01:11:43.855Z      warn    internal@v0.86.0/warning.go:40  Using the 0.0.0.0 address exposes this server to every network interface, which may facilitate Denial of Service attacks       {"kind": "extension", "name": "health_check", "documentation": "https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks"}
otel-collector  | 2025-11-08T01:11:43.858Z      info    extensions/extensions.go:38     Extension started.      {"kind": "extension", "name": "health_check"}
otel-collector  | 2025-11-08T01:11:43.859Z      warn    internal@v0.86.0/warning.go:40  Using the 0.0.0.0 address exposes this server to every network interface, which may facilitate Denial of Service attacks       {"kind": "receiver", "name": "otlp", "data_type": "traces", "documentation": "https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks"}
otel-collector  | 2025-11-08T01:11:43.859Z      info    otlpreceiver@v0.86.0/otlp.go:83 Starting GRPC server    {"kind": "receiver", "name": "otlp", "data_type": "traces", "endpoint": "0.0.0.0:4317"}
otel-collector  | 2025-11-08T01:11:43.863Z      warn    internal@v0.86.0/warning.go:40  Using the 0.0.0.0 address exposes this server to every network interface, which may facilitate Denial of Service attacks       {"kind": "receiver", "name": "otlp", "data_type": "traces", "documentation": "https://github.com/open-telemetry/opentelemetry-collector/blob/main/docs/security-best-practices.md#safeguards-against-denial-of-service-attacks"}
otel-collector  | 2025-11-08T01:11:43.863Z      info    otlpreceiver@v0.86.0/otlp.go:101        Starting HTTP server    {"kind": "receiver", "name": "otlp", "data_type": "traces", "endpoint": "0.0.0.0:4318"}
otel-collector  | 2025-11-08T01:11:43.863Z      info    healthcheck/handler.go:132      Health Check state change       {"kind": "extension", "name": "health_check", "status": "ready"}
otel-collector  | 2025-11-08T01:11:43.863Z      info    service@v0.86.0/service.go:161  Everything is ready. Begin running and processing data.
app-java        |
app-java        |   .   ____          _            __ _ _
app-java        |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
app-java        | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
app-java        |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
app-java        |   '  |____| .__|_| |_|_| |_\__, | / / / /
app-java        |  =========|_|==============|___/=/_/_/_/
app-java        |
app-java        |  :: Spring Boot ::                (v3.5.5)
app-java        |
app-java        | 2025-11-08T01:11:47.005Z  INFO 7 --- [app-java] [           main] c.example.app_java.AppJavaApplication    : Starting AppJavaApplication v0.0.1-SNAPSHOT using Java 21.0.8 with PID 7 (/app/app.jar started by root in /app)
app-java        | 2025-11-08T01:11:47.018Z  INFO 7 --- [app-java] [           main] c.example.app_java.AppJavaApplication    : No active profile set, falling back to 1 default profile: "default"
app-java        | 2025-11-08T01:11:48.550Z  INFO 7 --- [app-java] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
app-java        | 2025-11-08T01:11:48.584Z  INFO 7 --- [app-java] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
app-java        | 2025-11-08T01:11:48.585Z  INFO 7 --- [app-java] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.44]
app-java        | 2025-11-08T01:11:48.629Z  INFO 7 --- [app-java] [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
app-java        | 2025-11-08T01:11:48.630Z  INFO 7 --- [app-java] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1539 ms
app-java        | 2025-11-08T01:11:49.378Z  INFO 7 --- [app-java] [           main] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 1 endpoint beneath base path '/actuator'
app-java        | 2025-11-08T01:11:49.495Z  INFO 7 --- [app-java] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
app-java        | 2025-11-08T01:11:49.513Z  INFO 7 --- [app-java] [           main] c.example.app_java.AppJavaApplication    : Started AppJavaApplication in 3.138 seconds (process running for 6.183)
```

Si ves logs similares, todo está OK, pero validemos.


### Ventana CLI 02

Ejecuta, para ver que contenedores están en ejecución:
```console
%> docker ps
CONTAINER ID   IMAGE                                         COMMAND                  CREATED         STATUS         PORTS                                                                                                                      NAMES
b7541b0ef121   final-app-java                                "sh -c 'java $JAVA_O…"   6 minutes ago   Up 6 minutes   0.0.0.0:8080->8080/tcp                                                                                                     app-java
90d114c46cba   otel/opentelemetry-collector-contrib:0.86.0   "/otelcol-contrib --…"   6 minutes ago   Up 6 minutes   0.0.0.0:4317->4317/tcp, 0.0.0.0:8888->8888/tcp, 0.0.0.0:13133->13133/tcp, 0.0.0.0:55681->55681/tcp, 55678-55679/tcp        otel-collector
57c9073bb105   jaegertracing/all-in-one:1.49                 "/go/bin/all-in-one-…"   6 minutes ago   Up 6 minutes   5775/udp, 0.0.0.0:9411->9411/tcp, 5778/tcp, 0.0.0.0:14250->14250/tcp, 6831-6832/udp, 14268/tcp, 0.0.0.0:16686->16686/tcp   jaeger
```

Vemos 3 contenedores ejecutandose, entonces, validado todo OK.


---

## 🚀 Resultado esperado
- Abre Jaeger, debe estar todo limpio: [http://localhost:16686](http://localhost:16686)
- Ejecutar `curl http://localhost:8080/pago` o pega la URL en un browser, invoca varias veces.
- Regresa a Jaeger, debes ver ahora las trazas reflejadas en [http://localhost:16686](http://localhost:16686)
- Seleccionar el servicio `app-java` en Jaeger y explorar los spans generados.

* Puedes ver el demo aquí:[demoFase01.mp4] (Demo Fase 01. Completo).

Listo, estás capturando traces con intrumentación automática de OpenTelemetry.
---

## 🔍 Conceptos aplicados

- Instrumentación automática (Java Agent)
- Recolección con OTLP gRPC
- Exportación vía protocolo Zipkin
- Visualización distribuida con Jaeger
- Separación de componentes (Collector desacoplado)
- Multi-stage Docker build

---

## Detener todo.

Puedes detener todo de la siguiente forma:
### Ventana CLI 02

Ejecuta:
```console
%> docker-compose down
[+] Running 4/4
 ✔ Container app-java                Removed                                                                                                                                             10.5s
 ✔ Container otel-collector          Removed                                                                                                                                              0.4s
 ✔ Container jaeger                  Removed                                                                                                                                              0.4s
 ✔ Network final_observabilidad-net  Removed                                                                                                                                              0.3s
```

Listo. Todo detenido.
Si quieres conocer que más instrucciones puedes ejecutar, revisa otras operaciones que te pueden ayudar en este camino de aprendizaje:
[Otras operaciones útiles](../../ext/lab-operaciones-extendido.md)

## 📘 Próxima fase (Fase 2)

> Integración de logs distribuidos y correlación de trazas con herramientas como Loki o Fluent Bit.

---
