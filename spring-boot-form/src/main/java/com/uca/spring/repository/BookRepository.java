package com.uca.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.uca.spring.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

        @Query("SELECT x "
                        + " FROM Book x "
                        + "WHERE"
                        + "  (:idBook is null or :idBook = x.idBook )   "
                        + " and (:name is null or x.name = :name ) "
                        + " and (:description is null or x.description = :description ) "
                        + " and (:synopsis is null or x.synopsis = :synopsis ) "
                        + " and (:author is null or x.author = :author ) "
                        + " and (:isbn is null or x.isbn = :isbn ) ")
        Page<Book> findByFilters(Pageable page, @Param("idBook") Integer idBook, @Param("name") String name,
                        @Param("description") String description, @Param("synopsis") String synopsis,
                        @Param("author") String author, @Param("isbn") String isbn);

        @Query(value = "SELECT  x.id_book  ,  x.name  ,  x.description  ,  x.synopsis  , x.author, x.isbn"
                        + " FROM book x "
                        + "WHERE"
                        + "  (:idBook is null or :idBook = x.id_book ) "
                        + " and (:name is null or x.name = :name ) "
                        + " and (:description is null or x.description = :description ) "
                        + " and (:synopsis is null or x.synopsis = :synopsis ) "
                        + " and (:author is null or x.author = :author ) "
                        + " and (:isbn is null or x.isbn = :isbn ) ", nativeQuery = true)
        List<Object[]> findByFilters(@Param("idBook") Integer idBook, @Param("name") String name,
                        @Param("description") String description, @Param("synopsis") String synopsis,
                        @Param("author") String author, @Param("isbn") String isbn);

        @Procedure(procedureName = "DELETE_BOOK")
        void deletePerson(@Param("p_id_book") Integer p_book);

        @Query(value = "CALL DELETE_BOOK(?)", nativeQuery = true)
        void deletePersonNative(@Param("P_ID_BOOK") Integer p_book);

        @Query(value = "CALL FIND_BOOK(?)", nativeQuery = true)
        List<Book> getPerson(@Param("p_book") Integer p_book);

        @Procedure(procedureName = "GET_TOTAL_BOOKS")
        Integer countPerson();

        @Query(value = "SELECT GET_NAME(?1)", nativeQuery = true)
        String getName(Integer book);

        List<Book> findByisbn(String isbn);
}