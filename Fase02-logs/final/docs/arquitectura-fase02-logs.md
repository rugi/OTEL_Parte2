''' mermaid
graph TD
A[app-java] --> B[docker logs]
B --> C[promtail]
B --> D[fluent-bit]
C --> E[loki]
D --> E[loki]
E --> F[grafana]
'''