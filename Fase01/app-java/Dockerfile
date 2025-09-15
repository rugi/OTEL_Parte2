FROM eclipse-temurin:21-jdk as builder

WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests

# Runtime image
FROM  eclipse-temurin:21-jdk

ENV OTEL_SERVICE_NAME=app-java
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://otel-collector:4317

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Descarga del agente OpenTelemetry
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.33.5/opentelemetry-javaagent.jar /otel/opentelemetry-javaagent.jar

ENV JAVA_OPTS="-javaagent:/otel/opentelemetry-javaagent.jar"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
