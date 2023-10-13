package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String puName = "pu-name";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none"); // create ,update, none

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            String jpql ="SELECT p FROM Product  p";
//            String jpql ="SELECT p FROM Product  p WHERE p.price >1500";

//            String jpql ="SELECT p FROM Product  p WHERE p.price > :price AND p.name LIKE  :name";


            // "SELECT p FROM Product  p" ==> fetch all attributes of the product entity from the current context
            // "SELECT * FROM Product" ==> fetch all columns from the table product
//          TypedQuery<Product> q= em.createQuery(jpql, Product.class);
//
//          q.setParameter("price",1500);
//          q.setParameter("name","%a%");

//
//           List<Product> list = q.getResultList();
//
//           for (Product p : list){
//               System.out.println(p);
//           }


//           String jpql ="SELECT AVG(p.price) FROM Product  p";
//           TypedQuery<Double> q= em.createQuery(jpql, Double.class);
//
//            Double  avg =q.getSingleResult();
//            System.out.println(avg);


//            String jpql ="SELECT count (p.price) FROM Product  p";
//            TypedQuery<Long> q= em.createQuery(jpql, Long.class);
//
//            Long  avg =q.getSingleResult();
//            System.out.println(avg);


            String jpql ="SELECT p.name,p.price FROM Product  p";

            TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);

            q.getResultList().forEach(objects -> {      //form of Array
                System.out.println(objects[0]+ " "+objects[1]);
            });






            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}