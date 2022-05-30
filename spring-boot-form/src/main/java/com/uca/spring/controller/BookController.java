package com.uca.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uca.spring.jqgrid.JqgridFilter;
import com.uca.spring.jqgrid.JqgridResponse;
import com.uca.spring.model.Book;
import com.uca.spring.poi.LayOutDynamic;
import com.uca.spring.poi.Writer;
import com.uca.spring.repository.BookRepository;
import com.uca.spring.util.CboFilter;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/indexPerson")
    public ModelAndView indexPerson() {
        ModelAndView mv = new ModelAndView();
      
        mv.setViewName("person/index.jsp");
        return mv;
    }


    @RequestMapping(value = "/gridPerson", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody JqgridResponse<Book> gridPerson(
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sidx,
            @RequestParam(value = "sord", required = false) String sord) {

        Page<Book> list = bookRepository.findByFilters(
                PageRequest.of(page - 1, rows,
                        Sort.by(sord.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sidx)),
                JqgridFilter.getFieldInteger(filters, "idBook"), JqgridFilter.getField(filters, "name"),
                JqgridFilter.getField(filters, "description"), JqgridFilter.getField(filters, "rating"),
                JqgridFilter.getField(filters, "duration"), JqgridFilter.getField(filters, "synopsis"));

        JqgridResponse<Book> jqgridPerson = new JqgridResponse<Book>();
        return jqgridPerson.jGridFill(list, page, rows);
    }

  
    @RequestMapping(value = "/exportPerson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void exportPerson(
            HttpServletResponse response, @RequestParam(value = "filters", required = false) String filters) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("libro");

        List<String> header = new ArrayList<String>();
        header.add("idBook");
        header.add("name");
        header.add("synopsis");
        header.add("auhtor");
        header.add("isbn");

        LayOutDynamic.buildReport(worksheet, "Book", header);

        List<Object[]> list = bookRepository.findByFilters(
                JqgridFilter.getFieldInteger(filters, "idBook"), JqgridFilter.getField(filters, "name"),
                JqgridFilter.getField(filters, "description"), JqgridFilter.getField(filters, "duration"),
                JqgridFilter.getField(filters, "synopsis"), JqgridFilter.getField(filters, "rating"));

        LayOutDynamic.fillReport(worksheet, header.size(), list);
        String fileName = "Book.xls";
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        Writer.write(response, worksheet);
    }

    
    @RequestMapping(value = "/savePerson", method = RequestMethod.POST)
    public @ResponseBody String savePerson(@ModelAttribute("Book") @Validated Book Book) {

        Book.setIdBook(getRandomId());
        bookRepository.save(Book);
        return null;
    }

    /**
     * Controlador para eliminar por medio de Ajax
     * 
     * @return String pero se le coloca null para indicar que no hubo ningun error
     * @Param Modelo que vendra lleno para eliminarlo directamente en la base de
     *        datos, se elimina por medio del @Id
     * @author walter_salazarg@hotmail.com
     * @version 1.0
     */
    @RequestMapping(value = "/deletePerson", method = RequestMethod.DELETE)
    public @ResponseBody String deletePerson(@ModelAttribute("Book") Book Book) {
        bookRepository.delete(Book);
        return null;
    }

    @RequestMapping(value = {
            "/cbofilterPerson" }, method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody List<CboFilter> cbofilterMunicipioByDep() {

        List<Book> persons = bookRepository.findAll();
        List<CboFilter> response = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            response.add(new CboFilter(persons.get(i).getIdBook().toString(), persons.get(i).getName()));
        }
        return response;
    }
    
    private Integer getRandomId() {
        Random rand = new Random(); // instance of random class
        int upperbound = 2000;
        return Math.abs((Integer) (rand.nextInt() * upperbound));
    }
}