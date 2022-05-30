package com.uca.spring.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "BOOK")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "ID_BOOK")
    Integer idBook;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SYNOPSIS")
    public String synopsis;

    @Column(name = "AUTHOR")
    public String author;

    @Column(name = "ISBN")
    public String isbn;
}