package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;

public class ClienteJpaDao {
	
	 private static ClienteJpaDao instance;
     protected EntityManager entityManager;
     
     public static ClienteJpaDao getInstance(){
               if (instance == null){
                        instance = new ClienteJpaDao();
               }
               
               return instance;
     }

     private ClienteJpaDao() {
               entityManager = getEntityManager();
     }

     private EntityManager getEntityManager() {
               EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
               if (entityManager == null) {
                        entityManager = factory.createEntityManager();
               }

               return entityManager;
     }

     public Cliente getById(final int id) {
               return entityManager.find(Cliente.class, id);
     }

     @SuppressWarnings("unchecked")
     public List<Cliente> findAll() {
               return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
     }

     public void persist(Cliente cliente) {
               try {
                        entityManager.getTransaction().begin();
                        entityManager.persist(cliente);
                        entityManager.getTransaction().commit();
               } catch (Exception ex) {
                        ex.printStackTrace();
                        entityManager.getTransaction().rollback();
               }
     }

     public void merge(Cliente cliente) {
               try {
                        entityManager.getTransaction().begin();
                        entityManager.merge(cliente);
                        entityManager.getTransaction().commit();
               } catch (Exception ex) {
                        ex.printStackTrace();
                        entityManager.getTransaction().rollback();
               }
     }

     public void remove(Cliente cliente) {
               try {
                        entityManager.getTransaction().begin();
                        cliente = entityManager.find(Cliente.class, cliente.getId());
                        entityManager.remove(cliente);
                        entityManager.getTransaction().commit();
               } catch (Exception ex) {
                        ex.printStackTrace();
                        entityManager.getTransaction().rollback();
               }
     }

     public void removeById(final int id) {
               try {
                        Cliente cliente = getById(id);
                        remove(cliente);
               } catch (Exception ex) {
                        ex.printStackTrace();
               }
     }

}
