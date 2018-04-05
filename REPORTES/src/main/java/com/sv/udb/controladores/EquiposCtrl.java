/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controladores;

import com.sv.udb.modelos.Equipos;
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
public class EquiposCtrl {
    private final Connection conn;

    public EquiposCtrl() {
        
        this.conn = new Conexion().getConn();
    }
    /**
     *
     * @param nombEqui
     * @param descEqui
     * @return
     */
    public boolean guar(String nombEqui, String descEqui)
    {
        boolean resp = false;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("INSERT INTO equipos VALUES(NULL, ?, ?)");
            cmd.setString(1, nombEqui);
            cmd.setString(2, descEqui);
            cmd.executeUpdate();
            resp = true;
        }
        catch (Exception e)
        {
            System.err.println("Error al guardar Equipos: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException e)
            {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resp;
    }
    
    /**
     *
     * @return
     */
    public List<Equipos> consTodo()
    {
       List<Equipos> resp = new ArrayList<>();
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM equipos");
            ResultSet rs = cmd.executeQuery();
            while(rs.next())
            {
                resp.add(new Equipos(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
    /**
     *
     * @param codiEqui
     * @return
     */
    public Equipos cons(int codiEqui)
    {
       Equipos resp = null;
        try
        {
            PreparedStatement cmd = this.conn.prepareStatement("SELECT * FROM equipos WHERE codi_equi = ?");
            cmd.setInt(1, codiEqui);
            ResultSet rs = cmd.executeQuery();
            if(rs.next())
            {
                resp = new Equipos(rs.getInt(1),rs.getString(2), rs.getString(3));
            }
        }
        catch(SQLException ex)
        {
            System.err.println("Error al consultar Equipos: " + ex.getMessage());
        }
        finally
        {
            try
            {
                if(this.conn != null)
                {
                    if(!this.conn.isClosed())
                    {
                        this.conn.close();
                    }
                }
            }
            catch(SQLException ex)
            {
                System.err.println("Error al cerrar la conexión");
            }
        }
        return resp;
    }
    
    /**
     *
     * @param id
     * @param nomb
     * @param desc
     * @return
     */
    public boolean actualizar(int id,String nomb, String desc){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("UPDATE equipos SET nomb_equi=?,desc_equi=? WHERE codi_equi =?");
            cmd.setString(1, nomb);
            cmd.setString(2, desc);
            cmd.setInt(3, id);
            cmd.executeUpdate();
            resp = true;
        } catch (Exception ex) {
            System.err.println("Error al modificar equipos" + ex.getMessage());
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
    
    /**
     *
     * @param id
     * @return
     */
    public boolean eliminar(int id){
        boolean resp = false;
        
        try {
            PreparedStatement cmd = this.conn.prepareStatement("DELETE FROM equipos WHERE codi_equi=?");
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
