package com.uca.spring.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uca.spring.model.Departamento;
import com.uca.spring.repository.DepartamentoRepository;
import com.uca.spring.util.CboFilter;

@Controller
@RequestMapping("/")
public class DepartamentoController {

  @Autowired
  DepartamentoRepository departamentoRepository;


  /**
   *Controller for get FK
   *
   * @return List<CboFilter> Return id and Description
   * @author Wsalazar
   * @version 1.0
   */
  @RequestMapping(value = {"/cbofilterDepartamento"}, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  public @ResponseBody
  List<CboFilter> cbofilterDepartamento() {
    List<Departamento> deparments = departamentoRepository.findAll();
      List<CboFilter> response = new ArrayList<>();
      for (int i = 0; i < deparments.size(); i++) {
          response.add(new CboFilter(deparments.get(i).getIdDepartamento().toString(), deparments.get(i).getDescripcion()));
      }
      return response;
  }

}
