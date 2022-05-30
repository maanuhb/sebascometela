package com.uca.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import com.uca.spring.model.Book;
import com.uca.spring.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        System.out.println("Entering index");
        model.setViewName("index.jsp");
        return model;

    }

   @RequestMapping("/")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login.jsp");
        populateDatabase();
        return model;
    }

    private void populateDatabase() {
        Book book = new Book();
        if (bookRepository.count() > 0) {
            return;
        }

        List<String> names = new ArrayList<String>();
        names.add("Pirates of caribean");
        names.add("Pirates of caribean I");
        names.add("Pirates of caribean II");
        names.add("Fantastic beast and where to find them");
        names.add("Jurassic World: Fallen Kindom");
        List<String> description = new ArrayList<String>();
        description.add("A movie of pirates I");
        description.add("A movie of pirates and dead people");
        description.add("A movie of pirates and importal people");
        description.add("A movie of a magic beast");
        description.add("A movie of dinosaurs");
        List<String> synopsis = new ArrayList<String>();
        synopsis.add("Jack Sparrow is a pirate and he want recover his people");
        synopsis.add("Jack Sparrow will fight wit a deadly men");
        synopsis.add("Jack Sparrow will search sirens");
        synopsis.add("New Scamander will show a lot of Fantastic beast");
        synopsis.add("A new jurassic park is open but all dinosaurs will eat the people");
        List<String> author = new ArrayList<String>();
        author.add("100");
        author.add("200");
        author.add("300");
        author.add("400");
        author.add("500");
        List<String> isbn = new ArrayList<String>();
        isbn.add("8.021");
        isbn.add("7.1");
        isbn.add("9.1");
        isbn.add("3.1");
        isbn.add("9.9");

        for (int i = 0; i < 5; i++) {
            book = new Book();
            book.setIdBook(getRandomId());
            book.setName(names.get(i));
            book.setAuthor(author.get(i));
            book.setSynopsis(synopsis.get(i));
            book.setDescription(description.get(i));
            book.setIsbn(isbn.get(i));
            bookRepository.save(book);
        }
    }

    private Integer getRandomId() {
        Random rand = new Random(); // instance of random class
        int upperbound = 2000;
        return Math.abs((Integer) (rand.nextInt() * upperbound));
    }

}
