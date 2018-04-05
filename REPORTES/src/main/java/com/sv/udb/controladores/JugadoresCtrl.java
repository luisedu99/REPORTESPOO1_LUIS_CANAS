/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Equipos;
import com.sv.udb.modelos.Jugadores;
import com.sv.udb.modelos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;  
/**
 *
 * @author luisedu
 */
public class JugadoresCtrl {
    
    private final Connection conn;

    /**
     *
     */
    public JugadoresCtrl() {
        this.conn = new Conexion().getConn();
    }
    
    /**
     *
     * @param codigo
     * @param nombre
     * @param edad
     * @param estatura
     * @param peso
     * @return
     */
    public boolean guardar(Equipos codigo, String nombre, int edad, double estatura, double peso){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("insert into jugadores values(null,?,?,?,?,?)");
            cmd.setInt(1, codigo.getCodiEqui());
            cmd.setString(2, nombre);
            cmd.setInt(3,edad);
            cmd.setDouble(4, estatura);
            cmd.setDouble(5, peso);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al guardar jugadores" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion en jugadores: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Jugadores obtenerId(int id){
        Jugadores j = null;
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT j.codi_juga, e.*, j.nomb_juga, j.edad_juga, j.altu_juga, j.peso_juga FROM jugadores j INNER JOIN equipos e ON j.codi_equi = e.codi_equi where j.codi_juga="+id);
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                j = new Jugadores(new Equipos(rs.getInt(2),rs.getString(3),rs.getString(4)),rs.getInt(1),rs.getString(5),Integer.parseInt(rs.getString(6)),rs.getDouble(7), rs.getDouble(8));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return j;
    }
    
    /**
     *
     * @return
     */
    public List<Jugadores> obtenerTodo(){
        List<Jugadores> resp = new ArrayList<>();
        try {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT j.codi_juga, e.*, j.nomb_juga, j.edad_juga, j.altu_juga, j.peso_juga FROM jugadores j INNER JOIN equipos e ON j.codi_equi = e.codi_equi");
            ResultSet rs = cmd.executeQuery();
            while (rs.next()) {
                resp.add(new Jugadores(new Equipos(rs.getInt(2),rs.getString(3),rs.getString(4)),rs.getInt(1),rs.getString(5),Integer.parseInt(rs.getString(6)),rs.getDouble(7), rs.getDouble(8)));
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar: " + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    } 
    
    /**
     *
     * @param codigo
     * @param codigoJ
     * @param nombre
     * @param edad
     * @param estatura
     * @param peso
     * @return
     */
    public boolean modificar(Equipos codigo,int codigoJ,String nombre, int edad, double estatura, double peso){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE jugadores SET nomb_juga=?, edad_juga=?, altu_juga=?, peso_juga=?, codi_equi=? WHERE codi_juga=?");
            cmd.setString(1, nombre);
            cmd.setInt(2, edad);
            cmd.setDouble(3, estatura);
            cmd.setDouble(4, peso);
            cmd.setInt(5, codigo.getCodiEqui());
            cmd.setInt(6, codigoJ);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al modificar jugador" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion en jugador: " + e.getMessage());
            }
        }
        
        return resp;
    }
    
    /**
     *
     * @param id
     * @return
     */
    public boolean eliminar(int id){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM jugadores WHERE codi_juga=?");
            cmd.setInt(1, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al eliminar equipos" + ex.getMessage());
        } finally {
            try {
                if (this.conn != null) {
                    if (!this.conn.isClosed()) {
                        this.conn.close();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion en equipos: " + e.getMessage());
            }
        }
        
        return resp;
    }
}
