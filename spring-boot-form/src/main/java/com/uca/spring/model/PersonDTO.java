package com.uca.spring.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PersonDTO implements Serializable {
	@Id
	@Column(name = "id_persona")
	Integer idPerson;
	@Column(name = "nombre")
    String nombre;
	@Column(name = "direccion")
    String direccion;
	@Column(name = "telefono")
    String telefono;
	@Column(name = "correo")
    String correo;
	@Column(name = "fecha_creacion")
    String fechaCreacion;
	@Column(name = "especialidad")
    String especialidad;
	@Column(name = "id_departamento")
    String idDepartamento;
	@Column(name = "departamento")
    String departamento;
	@Column(name = "id_municipio")
    String idMunicipio;
	@Column(name = "municipio")
    String municipio;
}
