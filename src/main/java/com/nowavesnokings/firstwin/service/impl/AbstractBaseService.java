package com.nowavesnokings.firstwin.service.impl;

import com.nowavesnokings.firstwin.dao.BaseRepository;
import com.nowavesnokings.firstwin.dao.extended.StoredProcedureQueryBuilder;
import com.nowavesnokings.firstwin.service.CurdService;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author ssx
 * @version V1.0
 * @className AbstractBaseService
 * @description baseService抽象类
 * @date 2021-01-05 15:49
 * @since 1.8
 */
public abstract class AbstractBaseService<T, ID, R extends BaseRepository<T, ID>> implements CurdService<T, ID> {
    @Autowired
    private R repository;

    protected R getRepository() {
        return repository;
    }

    @Override
    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }


    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public T getOne(ID id) {
        return repository.getOne(id);
    }

    /**
     * Save list.
     *
     * @param iterable the iterable
     * @return the list
     */
    @SafeVarargs
    @Override
    public final List<T> save(T... iterable) {
        return repository.save(iterable);
    }

    /**
     * Find one optional.
     *
     * @param predicate the predicate
     * @return the optional
     */
    @Override
    public Optional<T> findOne(Predicate predicate) {
        return repository.findOne(predicate);
    }

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    @Override
    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Find all page.
     *
     * @param predicate the predicate
     * @param pageable  the pageable
     * @return the page
     */
    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    /**
     * Find all list.
     *
     * @param predicate the predicate
     * @return the list
     */
    @Override
    public List<T> findAll(Predicate predicate) {
        return repository.findAll(predicate);
    }

    /**
     * Find all list.
     *
     * @param predicate the predicate
     * @param sort      the sort
     * @return the list
     */
    @Override
    public List<T> findAll(Predicate predicate, Sort sort) {
        return repository.findAll(predicate, sort);
    }

    /**
     * Find all list.
     *
     * @param predicate       the predicate
     * @param orderSpecifiers the order specifiers
     * @return the list
     */
    @Override
    public List<T> findAll(Predicate predicate, OrderSpecifier<?>... orderSpecifiers) {
        return repository.findAll(predicate, orderSpecifiers);
    }

    /**
     * Find all list.
     *
     * @param orderSpecifiers the order specifiers
     * @return the list
     */
    @Override
    public List<T> findAll(OrderSpecifier<?>... orderSpecifiers) {
        return repository.findAll(orderSpecifiers);
    }

    /**
     * Find all by id list.
     *
     * @param ids the ids
     * @return the list
     */
    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    /**
     * Count long.
     *
     * @return the long
     */
    @Override
    public long count() {
        return repository.count();
    }

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    /**
     * Flush.
     */
    @Override
    public void flush() {
        repository.flush();
    }

    /**
     * Save and flush s.
     *
     * @param entity the entity
     * @return the s
     */
    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    /**
     * Query o.
     *
     * @param query the query
     * @return the o
     * @see JPQLQueryFactory#query()
     */
    @Override
    public <O> O query(Function<JPAQuery<?>, O> query) {
        return repository.query(query);
    }

    /**
     * Update o.
     *
     * @param update the update
     * @see JPQLQueryFactory#update(EntityPath)
     */
    @Override
    public void update(Consumer<JPAUpdateClause> update) {
        repository.update(update);
    }

    /**
     * Jpa sql query o.
     *
     * @param query the query
     * @return the o
     */
    @Override
    public <O> O jpaSqlQuery(Function<JPASQLQuery<T>, O> query) {
        return repository.jpaSqlQuery(query);
    }

    /**
     * Delete where long.
     *
     * @param predicate the predicate
     * @return the long
     */
    @Override
    public long deleteWhere(Predicate predicate) {
        return repository.deleteWhere(predicate);
    }

    /**
     * jpaQuery 执行子查询.
     *
     * @param query the query
     * @return the sub query expression
     */
    @Override
    public SubQueryExpression<T> jpaSqlSubQuery(Function<JPASQLQuery<T>, SubQueryExpression<T>> query) {
        return repository.jpaSqlSubQuery(query);
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
        return repository.executeStoredProcedure(name, query);
    }
}
