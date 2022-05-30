package com.uca.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uca.spring.model.Municipio;
import com.uca.spring.repository.MunicipioRepository;
import com.uca.spring.util.CboFilter;

@Controller
@RequestMapping("/")
public class MunicipioController {


  @Autowired
  MunicipioRepository municipioRepository;


  /**
   * Controller for get FK
   *
   * @return List<CboFilter> Return id and Description
   * @author Wsalazar
   * @version 1.0
   */
  @RequestMapping(value = {"/cbofilterMunicipioByDep"}, method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8")
  public @ResponseBody List<CboFilter> cbofilterMunicipioByDep(
      @RequestParam(value = "idDepartamento", required = false) Integer idDepartamento) {

    List<Municipio> listMunicipio = municipioRepository.getMunicipalitiesByIdDepartament(idDepartamento);
    List<CboFilter> response = new ArrayList<>();
    for (int i = 0; i < listMunicipio.size(); i++) {
      response.add(new CboFilter(listMunicipio.get(i).getIdMunicipio().toString(),
          listMunicipio.get(i).getDescripcion()));
    }
    return response;
  }


  /**
   *Controller for get FK
   *
   * @return List<CboFilter> Return id and Description
   * @author Wsalazar
   * @version 1.0
   */
  @RequestMapping(value = {"/cbofilterMunicipio"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public @ResponseBody
  List<CboFilter> cbofilterMunicipio() {
    List<Municipio> municipios = municipioRepository.findAll();
      List<CboFilter> response = new ArrayList<>();
      for (int i = 0; i < municipios.size(); i++) {
          response.add(new CboFilter(municipios.get(i).getIdMunicipio().toString(), municipios.get(i).getDescripcion()));
      }
      return response;
  }

}
