/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app_java.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
public class HelloController {

    @GetMapping("/pago")
    public String pago() throws InterruptedException {
        System.out.println(":.Entrando a pago.");
        // Simula procesamiento variable
        Thread.sleep((long) (500 + Math.random() * 1500));
        return "pago realizado";
    }
}
