package com.nowavesnokings.firstwin.dao.extended;

import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author ssx
 * @version V1.0
 * @className EnableBaseRepositoryFactoryBean
 * @description 自定义repository工厂bean类
 * @date 2021-01-12 12:19
 * @since 1.8
 */
public class ExtendedBaseRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {
    private EntityManager entityManager;
    private Supplier<JPASQLQuery<?>> jpaSqlFactory;
    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter;

    public ExtendedBaseRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
        this.escapeCharacter = EscapeCharacter.DEFAULT;
    }

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.entityManager = entityManager;
    }

    @Autowired
    public void setJpaSqlFactory(Supplier<JPASQLQuery<?>> jpaSqlFactory) {
        this.jpaSqlFactory = jpaSqlFactory;
    }

    @Override
    @Autowired
    public void setEntityPathResolver(ObjectProvider<EntityPathResolver> resolver) {
        super.setEntityPathResolver(resolver);
        this.entityPathResolver = resolver.getIfAvailable(() -> SimpleEntityPathResolver.INSTANCE);
    }

    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        ExtendedBaseRepositoryFactory baseRepositoryFactory = new ExtendedBaseRepositoryFactory(this.entityManager, this.jpaSqlFactory);
        baseRepositoryFactory.setEntityPathResolver(this.entityPathResolver);
        baseRepositoryFactory.setEscapeCharacter(this.escapeCharacter);
        baseRepositoryFactory.setRepositoryBaseClass(SimpleBaseRepository.class);
        return baseRepositoryFactory;
    }

    public void setEscapeCharacter(char escapeCharacter) {
        this.escapeCharacter = EscapeCharacter.of(escapeCharacter);
    }
}
