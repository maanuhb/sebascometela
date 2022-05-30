package com.uca.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "PERSON")
public class Person implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "ID_PERSONA")
    Integer idPerson;
    
    @Column(name = "NOMBRE")
    String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    public String telefono;

    @Column(name = "CORREO")
    String correo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy  HH:mm", timezone = "GMT-6")
    @Column(name = "FECHA_CREACION", nullable = true, updatable = false)
    @CreationTimestamp
    Date fechaCreacion;

    @Column(name = "ESPECIALIDAD",length =100, nullable = true)
    String especialidad;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumns({@JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")})
    Departamento departamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumns({@JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "ID_MUNICIPIO")})
    Municipio municipio;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JsonIgnore
    List<PersonPhone> personPhones;
    

    /**
    * Metodo para obtener la llave primaria del padre de la entidad
    * @return el tipo de dato de la llave primaria
    * @author Generador-Safi
    * @version 1.0
    **/
    public    Integer getCatDepartamentoDelegate(){
        return (this.departamento== null) ? null : this.departamento.getIdDepartamento();
    }

    /**
    * Metodo para obtener la descripcion de la tabla, por defecto coloca la llave primaria del padre de la entidad
    * @return String que debe contener la descripcion de la columna Padre
    * @author Generador-Safi
    * @version 1.0
    **/
    public    String getCatDepartamentoDescriptionDelegate(){
        return (this.departamento== null) ? null : this.departamento.getDescripcion();
    }

    /**
    * Metodo para  setear la llave primaria del padre de la entidad
    * @return void
    * @author Generador-Safi
    * @version 1.0
    **/
    public void setDepartamentoDelegate(    Integer  idDepartamento){
        if (idDepartamento == null) {
             this.departamento = null;
        }else {
             this.departamento = new Departamento();
             this.departamento.setIdDepartamento(idDepartamento);
         }
    }

    /**
    * Metodo para obtener la llave primaria del padre de la entidad
    * @return el tipo de dato de la llave primaria
    * @author Generador-Safi
    * @version 1.0
    **/
    public    Integer getMunicipioDelegate(){
        return (this.municipio== null) ? null : this.municipio.getIdMunicipio();
    }

    /**
    * Metodo para obtener la descripcion de la tabla, por defecto coloca la llave primaria del padre de la entidad
    * @return String que debe contener la descripcion de la columna Padre
    * @author Generador-Safi
    * @version 1.0
    **/
    public    String getMunicipioDescriptionDelegate(){
        return (this.municipio== null) ? null : this.municipio.getDescripcion();
    }

    /**
    * Metodo para  setear la llave primaria del padre de la entidad
    * @return void
    * @author Generador-Safi
    * @version 1.0
    **/
    public void setMunicipioDelegate(    Integer  idMunicipio){
        if (idMunicipio == null) {
             this.municipio = null;
        }else {
             this.municipio = new Municipio();
             this.municipio.setIdMunicipio(idMunicipio);
         }
    }
    
}
