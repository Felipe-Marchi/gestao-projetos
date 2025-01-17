package apoio;

import java.util.ArrayList;

// Utiliza Generics como tipo de dado
public interface IDAOT<T> {

    public String salvar(T o);

    public String editar(T o);

    public String excluir(int id);

    public ArrayList<T> consultarTodos();

    public ArrayList<T> consultar(String criterio);

    public T consultarId(int id);
}
