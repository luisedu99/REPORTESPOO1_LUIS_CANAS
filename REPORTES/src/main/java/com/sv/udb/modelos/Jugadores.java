/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.modelos;

/**
 *
 * @author luisedu
 */
public class Jugadores {
    private int codigoJ;
    private String nombre;
    private int edad;
    private double estatura;
    private double peso;
    private Equipos codiEqui; // la referencia -Llave foránea-

    public Jugadores() {
    }

    /**
     *
     * @param nombre
     * @param edad
     * @param estatura
     * @param peso
     */
    public Jugadores(Equipos codigo,int codigoJ, String nombre, int edad, double estatura, double peso) {
        this.codigoJ = codigoJ;
        this.nombre = nombre;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
        this.codiEqui = codigo;
    }
    
    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public int getEdad() {
        return edad;
    }

    /**
     *
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     *
     * @return
     */
    public double getEstatura() {
        return estatura;
    }

    /**
     *
     * @param estatura
     */
    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    /**
     *
     * @return
     */
    public double getPeso() {
        return peso;
    }

    /**
     *
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCodigoJ() {
        return codigoJ;
    }

    public void setCodigoJ(int codigoJ) {
        this.codigoJ = codigoJ;
    }

    public Equipos getCodiEqui() {
        return codiEqui;
    }

    public void setCodiEqui(Equipos codiEqui) {
        this.codiEqui = codiEqui;
    }
    
     @Override
    public String toString() {
        return nombre;
    }

}
