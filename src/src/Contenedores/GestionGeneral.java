package Contenedores;

import Excepciones.ObjetoNoRegistradoException;
import Excepciones.ObjetoYaRegistradoException;
import com.sun.jdi.ObjectCollectedException;

import java.util.HashSet;
import java.util.Iterator;

public class GestionGeneral<T> {
    private HashSet<T> listaRegistros;
    /// ------------------------------------------------------------------------------------------------------------

    public GestionGeneral() {
        this.listaRegistros = new HashSet<>();
    }

    /// ------------------------------------------------------------------------------------------------------------


    public HashSet<T> getListaRegistros() {
        return listaRegistros;
    }

    /// ------------------------------------------------------------------------------------------------------------

    /// METODOS PARA CLASE GENERICA

    /// AGREGAR
    public void agregarObjeto(T objeto) throws ObjetoYaRegistradoException
    {
        if(listaRegistros.contains(objeto))
        {
            /// quiere decir que el objeto ya se encuentra registrado
        throw new ObjetoYaRegistradoException("El objeto ya se encuentra registrado en el sistema");
        }
        listaRegistros.add(objeto);

    }

    /// ELIMINAR

    public void eliminarObjeto(T objeto) throws ObjetoNoRegistradoException
    {
        if(!listaRegistros.contains(objeto))
        {
            throw new ObjetoNoRegistradoException("El objeto no se encuentra registrado en el sistema para poder eliminarlo");
        }
        listaRegistros.remove(objeto);
    }


    /// BUSCAR

    public Boolean buscarObjetoBoolean(T objeto)
    {
        Boolean retorno = false;
        if(!listaRegistros.contains(objeto))
        {
         retorno = true;
        }
        return retorno;
    }

    public T buscarObjetoYretornarlo(T objeto)
    {
        Iterator<T> recorredor = listaRegistros.iterator();
        while (recorredor.hasNext())
        {
            T actual = recorredor.next();
            if(actual.equals(objeto))
            {
             /// quiere decir que encontro ese objeto
            return actual;
            }
        }
        return null;
    }

    /// ------------------------------------------------------------------------------------------------------------



}
