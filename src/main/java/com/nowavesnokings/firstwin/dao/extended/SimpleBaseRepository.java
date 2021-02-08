package com.nowavesnokings.firstwin.dao.extended;

import com.nowavesnokings.firstwin.dao.BaseRepository;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ssx
 * @version V1.0
 * @className SimpleBaseService
 * @description 自定义Repository实现类
 * @date 2021-01-06 18:19
 * @since 1.8
 */
@Transactional(readOnly = true)
public class SimpleBaseRepository<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;
    private Supplier<JPASQLQuery<T>> jpaSqlFactory;
    private EntityPath<T> entityPath;

    public SimpleBaseRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, Supplier<JPASQLQuery<T>> jpaSqlFactory) {
        super(entityInformation, entityManager, SimpleEntityPathResolver.INSTANCE);
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(HQLTemplates.DEFAULT, entityManager);
        this.jpaSqlFactory = jpaSqlFactory;
        this.entityPath = SimpleEntityPathResolver.INSTANCE.createPath(entityInformation.getJavaType());
    }

    /**
     * Save list.
     *
     * @param iterable the iterable
     * @return the list
     */
    @Override
    public List<T> save(T... iterable) {
        return saveAll(Arrays.asList(iterable));
    }

    /**
     * Query o.
     *
     * @param query the query
     * @return the o
     * @see com.querydsl.jpa.JPQLQueryFactory#query()
     */
    @Override
    public <O> O query(Function<JPAQuery<?>, O> query) {
        return query.apply(jpaQueryFactory.query());
    }

    /**
     * Update o.
     *
     * @param update the update
     * @see com.querydsl.jpa.JPQLQueryFactory#update(EntityPath)
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Consumer<JPAUpdateClause> update) {
        update.accept(jpaQueryFactory.update(entityPath));
    }

    /**
     * Jpa sql query o.
     *
     * @param query the query
     * @return the o
     */
    @Override
    public <O> O jpaSqlQuery(Function<JPASQLQuery<T>, O> query) {
        return query.apply(jpaSqlFactory.get());
    }

    /**
     * Delete where long.
     *
     * @param predicate the predicate
     * @return the long
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long deleteWhere(Predicate predicate) {
        return jpaQueryFactory.delete(entityPath).where(predicate).execute();
    }

    /**
     * jpaQuery 执行子查询.
     *
     * @param query the query
     * @return the sub query expression
     */
    @Override
    public SubQueryExpression<T> jpaSqlSubQuery(Function<JPASQLQuery<T>, SubQueryExpression<T>> query) {
        return jpaSqlQuery(query);
    }

    @Override
    protected JPQLQuery<T> createQuery(Predicate... predicate) {
        return (JPQLQuery<T>) super.createQuery(predicate);
    }

    /**
     * 执行存储过程.
     *
     * @param name  the name
     * @param query the query
     * @return the o
     */
    @Override
    public <O> O executeStoredProcedure(String name, Function<StoredProcedureQueryBuilder, O> query) {
        return query.apply(new StoredProcedureQueryBuilder());
    }
}
