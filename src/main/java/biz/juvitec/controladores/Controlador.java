/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package biz.juvitec.controladores;

import biz.juvitec.dao.DAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 * @param <T>
 */
public abstract class Controlador<T>{
    private final DAO<T> dao;
    public static final int NUEVO = 1;
    public static final int MODIFICAR = 2;
    public static final int ELIMINAR = 3;
    private T seleccionado;
    private final Class<T> seleccionadoClass;

    public T getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(T seleccionado) {
        this.seleccionado = seleccionado;
    }        

    public DAO<T> getDao() {
        return dao;
    }

    public Controlador(Class<T> clase) {
        this.seleccionadoClass = clase;
        this.dao = new DAO<>(clase);
    }        
    
    public Controlador(Class<T> clase, DAO<T> dao){
        this.seleccionadoClass = clase;
        this.dao = dao;
    }

    private boolean guardar(T objeto) {
        return this.dao.guardar(objeto);
    }

    private boolean modificar(T objeto) {
        return this.dao.modificar(objeto);
    }

    private boolean eliminar(T objeto) {
        return this.dao.eliminar(objeto);
    }
    
    public List<T> buscarTodos(){
        return this.dao.buscarTodos();
    }
    
    public T buscarPorId(Object id){
        return this.dao.buscarPorId(id);
    }
    
    public List<T> buscar(String nombre){
        return this.dao.buscar(nombre);
    }
    
    public int conteo(){
        return this.dao.contar();
    }
    
    public void prepararCrear(){
        try {
            seleccionado = seleccionadoClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean accion(int accion) {
        if (accion == Controlador.NUEVO) {
            return this.guardar(seleccionado);
        } else if (accion == Controlador.MODIFICAR) {
            return this.modificar(seleccionado);
        } else if (accion == Controlador.ELIMINAR) {
            return this.eliminar(seleccionado);
        }
        return false;
    }
}
