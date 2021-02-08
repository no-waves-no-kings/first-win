package com.nowavesnokings.firstwin.dao.extended;

import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author ssx
 * @version V1.0
 * @className EnableBaseRepositoryFactory
 * @description 自定义jparepository工厂类
 * @date 2021-01-12 11:12
 * @since 1.8
 */
public class ExtendedBaseRepositoryFactory extends JpaRepositoryFactory {
    private Supplier<JPASQLQuery<?>> jpaSqlFactory;

    public ExtendedBaseRepositoryFactory(EntityManager entityManager, Supplier<JPASQLQuery<?>> jpaSqlFactory) {
        super(entityManager);
        this.jpaSqlFactory = jpaSqlFactory;
    }

    @Override
    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType());
        Object repositoryViaReflection = getTargetRepositoryViaReflection(information, entityInformation, entityManager, jpaSqlFactory);
        Assert.isInstanceOf(JpaRepositoryImplementation.class, repositoryViaReflection);
        return (SimpleBaseRepository<?, ?>)repositoryViaReflection;
    }
}
