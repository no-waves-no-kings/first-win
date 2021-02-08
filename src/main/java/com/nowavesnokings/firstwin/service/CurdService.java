package com.nowavesnokings.firstwin.service;

import com.nowavesnokings.firstwin.dao.extended.StoredProcedureQueryBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
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
 * @className BaseService
 * @description 基础Service
 * @date 2021-01-05 14:10
 * @since 1.8
 */
public interface CurdService<T, ID> {
    /**
     * Save s.
     *
     * @param <S>    the type parameter
     * @param entity the entity
     * @return the s
     */
    <S extends T> S save(S entity);

    /**
     * Save list.
     *
     * @param iterable the iterable
     * @return the list
     */
    List<T> save(T... iterable);

    /**
     * Delete.
     *
     * @param entity the entity
     */
    void delete(T entity);

    /**
     * Delete by id.
     *
     * @param id the id
     */
    void deleteById(ID id);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Delete all.
     *
     * @param entities the entities
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Delete in batch.
     *
     * @param entities the entities
     */
    void deleteInBatch(Iterable<T> entities);

    /**
     * Delete all in batch.
     */
    void deleteAllInBatch();

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    T getOne(ID id);

    /**
     * Find one optional.
     *
     * @param predicate the predicate
     * @return the optional
     */
    Optional<T> findOne(Predicate predicate);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<T> findById(ID id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Find all list.
     *
     * @param sort the sort
     * @return the list
     */
    List<T> findAll(Sort sort);

    /**
     * Find all page.
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Find all page.
     *
     * @param predicate the predicate
     * @param pageable  the pageable
     * @return the page
     */
    Page<T> findAll(Predicate predicate, Pageable pageable);

    /**
     * Find all list.
     *
     * @param predicate the predicate
     * @return the list
     */
    List<T> findAll(Predicate predicate);

    /**
     * Find all list.
     *
     * @param predicate the predicate
     * @param sort      the sort
     * @return the list
     */
    List<T> findAll(Predicate predicate, Sort sort);

    /**
     * Find all list.
     *
     * @param predicate       the predicate
     * @param orderSpecifiers the order specifiers
     * @return the list
     */
    List<T> findAll(Predicate predicate, OrderSpecifier<?>... orderSpecifiers);

    /**
     * Find all list.
     *
     * @param orderSpecifiers the order specifiers
     * @return the list
     */
    List<T> findAll(OrderSpecifier<?>... orderSpecifiers);

    /**
     * Find all by id list.
     *
     * @param ids the ids
     * @return the list
     */
    List<T> findAllById(Iterable<ID> ids);

    /**
     * Count long.
     *
     * @return the long
     */
    long count();

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean existsById(ID id);

    /**
     * Flush.
     */
    void flush();

    /**
     * Save and flush s.
     *
     * @param <S>    the type parameter
     * @param entity the entity
     * @return the s
     */
    <S extends T> S saveAndFlush(S entity);

    /**
     * Query o.
     *
     * @param <O>   the type parameter
     * @param query the query
     * @return the o
     * @see com.querydsl.jpa.JPQLQueryFactory#query() com.querydsl.jpa.JPQLQueryFactory#query()
     */
    <O> O query(Function<JPAQuery<?>, O> query);

    /**
     * Update o.
     *
     * @param update the update
     * @see com.querydsl.jpa.JPQLQueryFactory#update(EntityPath) com.querydsl.jpa.JPQLQueryFactory#update(EntityPath)
     */
    void update(Consumer<JPAUpdateClause> update);

    /**
     * Jpa sql query o.
     *
     * @param <O>   the type parameter
     * @param query the query
     * @return the o
     */
    <O> O
    jpaSqlQuery(Function<JPASQLQuery<T>, O> query);

    /**
     * Delete where long.
     *
     * @param predicate the predicate
     * @return the long
     */
    long deleteWhere(Predicate predicate);

    /**
     * jpaQuery 执行子查询.
     *
     * @param query the query
     * @return the sub query expression
     */
    SubQueryExpression<T> jpaSqlSubQuery(Function<JPASQLQuery<T>, SubQueryExpression<T>> query);

    /**
     * 执行存储过程.
     *
     * @param <O>   the type parameter
     * @param name  the name
     * @param query the query
     * @return the o
     */
    <O> O executeStoredProcedure(String name, Function<StoredProcedureQueryBuilder, O> query);

}
