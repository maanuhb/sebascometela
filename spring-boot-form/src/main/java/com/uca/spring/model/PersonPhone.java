package com.uca.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "PERSON_PHONE")
public class PersonPhone implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PHONE")
    Integer idPhone;

    @Column(name = "NUMBER")
    String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumns({@JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA")})
    Person person;
    
    
    /**
     * Metodo para obtener la llave primaria del padre de la entidad
     *
     * @return el tipo de dato de la llave primaria
     * @author Generador-Safi
     * @version 1.0
    *
     */
    public Integer getPersonPhoneDelegate() {
        return (this.person == null) ? null : this.person.getIdPerson();
    }
    
}
