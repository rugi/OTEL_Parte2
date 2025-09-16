/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import com.example.app_java.beans.PagoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author admin
 */
@RestController
public class ServiceController {

    // creating a logger
    Logger logger
            = LoggerFactory.getLogger(ServiceController.class);

    @GetMapping("/ping")
    public String ping() throws InterruptedException {
        logger.info("ping:.");
        // Simula procesamiento variable
        Thread.sleep((long) (500 + Math.random() * 1500));
        return "OK";
    }

    @PostMapping(
            value = "/servicio/pagar",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<?> createPerson(@RequestBody PagoServicio pagoServicio) throws InterruptedException {
        logger.info("Pago con:" + pagoServicio);
        // Simulación de distintos errores para laboratorio
        if (pagoServicio.getIdServicio() > 0) {
            switch (pagoServicio.getIdServicio()) {                
                case 500 -> {
                    logger.error("HTTP 500. Simulando Error 500.");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .header("X-Lab-Scenario", "Simulacion-500")
                            .body("Simulación: Error 500");
                }
                case 404 -> {
                    logger.warn("HTTP 400. Simulando Error 400.");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .header("X-Lab-Scenario", "Simulacion-404")
                            .body("Simulación: No encontrado");
                }
                case 503 -> {
                    logger.error("HTTP 503. Simulando Error 503.");
                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .header("X-Lab-Scenario", "Simulacion-503")
                            .body("Simulación: Servicio no disponible");
                }
                default -> {
                    // que continùe el flujo.
                }
            }
        }

        // Caso normal: seguimos simulando tiempos aleatorios de respuesta.
        Thread.sleep((long) (500 + Math.random() * 1500));
        // respondemos CREATED con el mismo body de entrda.
        logger.info("Todo OK. Enviando respuesta.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Lab-Scenario", "CreacionExitosa")
                .body(pagoServicio);
    }
}
