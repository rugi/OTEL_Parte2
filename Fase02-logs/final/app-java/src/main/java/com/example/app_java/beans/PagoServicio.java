/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.app_java.beans;

/**
 *
 * @author admin
 */
public class PagoServicio {

    private int idServicio;
    private double monto;

    public PagoServicio() {
        super();
    }

    /**
     * @return the idServicio
     */
    public int getIdServicio() {
        return idServicio;
    }

    /**
     * @param idServicio the idServicio to set
     */
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String toString() {
        return new StringBuilder().append("{")
                .append("\"").append("idServicio").append("\"")
                .append(":")
                .append(this.idServicio)
                .append(",")
                .append("\"").append("monto").append("\"")
                .append(":")
                .append(this.monto)
                .append("}").toString();
    }
}
