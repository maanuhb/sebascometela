package com.uca.spring.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MUNICIPIO")
@Setter
@Getter
public class Municipio implements Serializable{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_MUNICIPIO")
  Integer idMunicipio;
  
  @Column(name = "ID_DEPARTAMENTO")
  Integer idDepartamento;
  
  @Column(name = "DESCRIPCION")
  String descripcion;

}
