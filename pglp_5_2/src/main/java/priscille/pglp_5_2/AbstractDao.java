package priscille.pglp_5_2;

import java.sql.Connection;

public abstract class AbstractDao<T> {
    /**
     * Connecteur.
     */
    protected Connection connect;
	/**
	 * Constructeur.
	 * @param c connecteur modifié
	 */
    public AbstractDao(Connection c) {
        connect = c;
    }
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
   public abstract T find(int id);
    /**
     * Ajoute un membre a la liste du personnel.
     * @param t Le membre a ajouter
     */
    public abstract T create(T t);
    /**
     * Modifie un membre du personnel
     * de la liste.
     * @param t Le membre a modifier
     * @param params Le parametre a modifier
     */
    public abstract T update(T t);
    /**
     * Retire un membre de la liste du personnel.
     * @param t Le membre a retirer
     */
    public abstract void delete(T t);
}

