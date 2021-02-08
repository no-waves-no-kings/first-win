package com.nowavesnokings.firstwin.dao;

import com.nowavesnokings.firstwin.dao.extended.StoredProcedureQueryBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author ssx
 * @version V1.0
 * @className BaseRepository
 * @description 基础Repository
 * @date 2021-01-05 13:16
 * @since 1.8
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
    /**
     * Save list.
     *
     * @param iterable the iterable
     * @return the list
     */
    List<T> save(T... iterable);

    @Override
    List<T> findAll(Predicate predicate);

    @Override
    List<T> findAll(Predicate predicate, Sort sort);

    @Override
    List<T> findAll(Predicate predicate, OrderSpecifier<?>... orderSpecifiers);

    @Override
    List<T> findAll(OrderSpecifier<?>... orderSpecifiers);

    /**
     * Query o.
     *
     * @param <O>   the type parameter
     * @param query the query
     * @return the o
     * @see JPQLQueryFactory#query() JPQLQueryFactory#query()JPQLQueryFactory#query()JPQLQueryFactory#query()JPQLQueryFactory#query()JPQLQueryFactory#query()JPQLQueryFactory#query()JPQLQueryFactory#query()
     */
    <O> O query(Function<JPAQuery<?>, O> query);

    /**
     * Update o.
     *
     * @param update the update
     * @see JPQLQueryFactory#update(EntityPath) JPQLQueryFactory#update(EntityPath)JPQLQueryFactory#update(EntityPath)JPQLQueryFactory#update(EntityPath)JPQLQueryFactory#update(EntityPath)JPQLQueryFactory#update(EntityPath)JPQLQueryFactory#update(EntityPath)
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
