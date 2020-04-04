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
    public AbstractDao(final Connection c) {
        connect = c;
    }
    /**
     * Retourne l'objet recherché.
     * @param id L'identifiant de l'objet
     * @return L'objet trouvé
     */
    public abstract T find(int id);
    /**
     * Ajoute un objet.
     * @param t L'objet a ajouter
     * @return L'objet créé
     */
    public abstract T create(T t);
    /**
     * Modifie un objet.
     * @param t L'objet a modifier
     * @return L'objet modifié
     */
    public abstract T update(T t);
    /**
     * Retire un objet.
     * @param t L'objet a retirer
     */
    public abstract void delete(T t);
}

