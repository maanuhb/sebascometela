package com.uca.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.uca.spring.model.PersonDTO;


public interface PersonDTORepository extends JpaRepository<PersonDTO, Integer>, PagingAndSortingRepository<PersonDTO, Integer> {

	@Query(value = "select  p.id_persona as id_persona, p.correo, p.direccion , p.especialidad , "
			+ "		p.fecha_creacion ,p.nombre , p.telefono , p.id_departamento ,d.descripcion as departamento, p.id_municipio , "
			+ "        m.descripcion as municipio "
			+ "from person p,municipio m, departamento d "
			+ "where p.id_departamento=d.id_departamento and p.id_municipio= m.id_municipio order by p.id_persona",nativeQuery = true,
			countQuery = "select  count(1) "
					+ "from person p,municipio m, departamento d "
					+ "where p.id_departamento=d.id_departamento and p.id_municipio= m.id_municipio order by p.id_persona")
	Page<PersonDTO> getPersonsDto(Pageable page);
	
	
	@Query(value = "select  p.id_persona as id_persona, p.correo, p.direccion , p.especialidad , "
			+ "		p.fecha_creacion ,p.nombre , p.telefono , p.id_departamento ,d.descripcion as departamento, p.id_municipio , "
			+ "        m.descripcion as municipio "
			+ "from person p,municipio m, departamento d "
			+ "where p.id_departamento=d.id_departamento and p.id_municipio= m.id_municipio order by p.id_persona",nativeQuery = true)
	List<PersonDTO> getPersons();
			
}
