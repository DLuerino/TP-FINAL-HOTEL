package Contenedores;

import Excepciones.ObjetoNoRegistradoException;
import Excepciones.ObjetoYaRegistradoException;
import com.sun.jdi.ObjectCollectedException;

import java.util.HashSet;

public class GestionGeneral<T> {
    private HashSet<T> listaRegistros;
    // ------------------------------------------------------------------------------------------------------------

    public GestionGeneral(HashSet<T> listaRegistros) {
        this.listaRegistros = new HashSet<>();
    }

    // ------------------------------------------------------------------------------------------------------------


    public HashSet<T> getListaRegistros() {
        return listaRegistros;
    }

    // ------------------------------------------------------------------------------------------------------------

    /// METODOS PARA CLASE GENERICA

    /// AGREGAR
    public void agregarElemento(T objeto) throws ObjetoYaRegistradoException
    {
        if(listaRegistros.contains(objeto))
        {
            /// quiere decir que el objeto ya se encuentra registrado
        throw new ObjetoYaRegistradoException("El objeto ya se encuentra registrado en el sistema");
        }
        listaRegistros.add(objeto);

    }

    /// ELIMINAR

    public void eliminarElemento(T objeto) throws ObjetoNoRegistradoException
    {
        if(!listaRegistros.contains(objeto))
        {
            throw new ObjetoNoRegistradoException("El objeto no se encuentra registrado en el sistema para poder eliminarlo");
        }
        listaRegistros.remove(objeto);
    }


    /// BUSCAR

    public Boolean buscarObjeto(T objeto)
    {
        Boolean retorno = false;
        if(!listaRegistros.contains(objeto))
        {
         retorno = true;
        }
        return retorno;
    }

    // ------------------------------------------------------------------------------------------------------------



}
