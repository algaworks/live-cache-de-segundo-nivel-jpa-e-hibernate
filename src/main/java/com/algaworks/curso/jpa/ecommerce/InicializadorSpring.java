package com.algaworks.curso.jpa.ecommerce;

import com.algaworks.curso.jpa.ecommerce.config.JpaConfig;
import com.algaworks.curso.jpa.ecommerce.model.Categoria;
import com.algaworks.curso.jpa.ecommerce.model.Entidade;
import com.algaworks.curso.jpa.ecommerce.repository.Produtos;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class InicializadorSpring {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();

        applicationContext.register(JpaConfig.class);

        applicationContext.scan(Entidade.class.getPackageName(),
                Produtos.class.getPackageName());

        applicationContext.refresh();

        EntityManagerFactory entityManagerFactory = applicationContext
                .getBean(EntityManagerFactory.class);

        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        Categoria categoria1 = entityManager1.find(Categoria.class, 1);
        log(cache.contains(Categoria.class, categoria1.getId()));

        Categoria categoria1DeNovo = entityManager2.find(Categoria.class, 1);

        cache.evictAll();
        cache.evict(Categoria.class);
        cache.evict(Categoria.class, 1);


        entityManager1.close();
        entityManager2.close();
        entityManagerFactory.close();

        applicationContext.close();

        log("Fim!");
    }

    private static void log(Object msg) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + msg);
    }
}
