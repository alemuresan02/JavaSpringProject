package org.utcn.springproject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.utcn.springproject.data.BookRepository;
import org.utcn.springproject.models.Book;
import org.utcn.springproject.models.BookType;

@Controller
@RequestMapping("home/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String displayAllBooks(Model model) {
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/index";
    }

    @GetMapping("create")
    public String displayCreateBookForm(Model model) {
        model.addAttribute("title", "Create Book");
        model.addAttribute(new Book());
        model.addAttribute("types", BookType.values());
        return "books/create";
    }

    @PostMapping("create")
    public String processCreateBookForm(@ModelAttribute @Valid Book newBook,
                                        Errors errors,
                                        Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Book");
            return "books/create";
        }

        bookRepository.save(newBook);
        return "redirect:/home/books";
    }

    @GetMapping("delete")
    public String displayDeleteBookForm(Model model) {
        model.addAttribute("title", "Delete Books");
        model.addAttribute("books", bookRepository.findAll());
        return "books/delete";
    }

    @PostMapping("delete")
    public String processDeleteBookForm(@RequestParam(required = false) int[] bookIds) {
        if(bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }

        return "redirect:/home/books";
    }

}