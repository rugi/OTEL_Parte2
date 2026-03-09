#!/bin/bash

echo "Generando tráfico contra app-java..."

for i in {1..20}
do
  curl -s http://localhost:8080/pago > /dev/null
  echo "request $i"
  sleep 1
done

echo "Tráfico generado."