package priscille.pglp_5_2;

import java.util.ArrayList;
import java.util.Map;

public interface Dao<T> {
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
    T get(int id);
    /**
     * Retourne la liste du personnel.
     * @return La liste du personnel.
     */
    ArrayList<T> getAll();
    /**
     * Ajoute un membre a la liste du personnel.
     * @param t Le membre a ajouter
     */
    void ajouter(T t);
    /**
     * Modifie un membre du personnel
     * de la liste.
     * @param t Le membre a modifier
     * @param params Le parametre a modifier
     */
    void update(T t, Map<String, Object> params);
    /**
     * Retire un membre de la liste du personnel.
     * @param t Le membre a retirer
     */
    void retirer(T t);
}
