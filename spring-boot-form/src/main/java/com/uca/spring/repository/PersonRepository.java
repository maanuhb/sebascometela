package com.uca.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.uca.spring.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("SELECT x "
			+ " FROM Person x "
			+ "WHERE"
			+ "  (:idPerson is null or :idPerson = x.idPerson )   "
			+ " and (:correo is null or x.correo = :correo ) "
			+ " and (:direccion is null or x.direccion = :direccion ) "
			+ " and (:especialidad is null or x.especialidad = :especialidad ) "
			+ " and (:fechaCreacion is null or x.fechaCreacion = :fechaCreacion ) "
			+ " and (:nombre is null or x.nombre = :nombre ) "
			+ " and (:telefono is null or x.telefono = :telefono ) "
			+ " and  (:fkidDepartamentodepartamento is null or :fkidDepartamentodepartamento = x.departamento.idDepartamento) "
			+ " and  (:fkidMunicipiomunicipio is null or :fkidMunicipiomunicipio = x.municipio.idMunicipio) ")
	Page<Person> findByFilters(Pageable page, @Param("idPerson") Integer idPerson, @Param("correo") String correo,
			@Param("direccion") String direccion, @Param("especialidad") String especialidad,
			@Param("fechaCreacion") String fechaCreacion, @Param("nombre") String nombre,
			@Param("telefono") String telefono,
			@Param("fkidDepartamentodepartamento") Integer fkidDepartamentodepartamento,
			@Param("fkidMunicipiomunicipio") Integer fkidMunicipiomunicipio);

	/**
	 * Metodo para obtener y filtrar el query de la entidad y usarlo para exportar a
	 * excel
	 * 
	 * @return Lista de tipo entidad
	 * @author walter_salazarg@hotmail.com
	 * @version 1.0
	 **/
	@Query(value = "SELECT  x.id_persona  ,  x.correo  ,  x.direccion  ,  x.especialidad  ,  x.fecha_creacion  ,  x.nombre  ,  x.telefono  ,  x.id_departamento  ,  x.id_municipio  "
			+ " FROM person x "
			+ "WHERE"
			+ "  (:idPerson is null or :idPerson = x.id_persona )   "
			+ " and (:correo is null or x.correo = :correo ) "
			+ " and (:direccion is null or x.direccion = :direccion ) "
			+ " and (:especialidad is null or x.especialidad = :especialidad ) "
			+ " and (:fechaCreacion is null or x.fecha_creacion = :fechaCreacion ) "
			+ " and (:nombre is null or x.nombre = :nombre ) "
			+ " and (:telefono is null or x.telefono = :telefono ) "
			+ " and  (:fkidDepartamentodepartamento is null or :fkidDepartamentodepartamento = x.id_departamento) "
			+ " and  (:fkidMunicipiomunicipio is null or :fkidMunicipiomunicipio = x.id_municipio) "
			+ " ORDER BY x.id_persona ASC ", nativeQuery = true)
	List<Object[]> findByFilters(@Param("idPerson") Integer idPerson, @Param("correo") String correo,
			@Param("direccion") String direccion, @Param("especialidad") String especialidad,
			@Param("fechaCreacion") String fechaCreacion, @Param("nombre") String nombre,
			@Param("telefono") String telefono,
			@Param("fkidDepartamentodepartamento") Integer fkidDepartamentodepartamento,
			@Param("fkidMunicipiomunicipio") Integer fkidMunicipiomunicipio);

	@Procedure(procedureName = "DELETE_PERSON")
	void deletePerson(@Param("p_id_persona") Integer p_person);

	@Query(value = "CALL DELETE_PERSON(?)", nativeQuery = true)
	void deletePersonNative(@Param("P_ID_PERSONA") Integer p_person);

	@Query(value = "CALL FIND_PERSON(?)", nativeQuery = true)
	List<Person> getPerson(@Param("p_person") Integer p_person);

	@Procedure(procedureName = "GET_TOTAL_PERSONS")
	Integer countPerson();

	@Query(value = "SELECT GET_NAME(?1)", nativeQuery = true)
	String getName(Integer person);

	List<Person> findByNombre(String nombre);
}
