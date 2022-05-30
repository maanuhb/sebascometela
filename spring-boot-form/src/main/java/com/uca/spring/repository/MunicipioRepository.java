package com.uca.spring.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.uca.spring.model.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

  @Query("Select x from Municipio x where x.idDepartamento=:idDepartamento ")
  List<Municipio> getMunicipalitiesByIdDepartament(@Param("idDepartamento")  Integer idDepartamento);
  
}
