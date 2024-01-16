package org.utcn.springproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.utcn.springproject.data.BookRepository;
import org.utcn.springproject.models.Book;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Optional;

@Controller
@RequestMapping("home/customer")
public class CustomerController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String displayAllBooks(Model model) {
        model.addAttribute("title", "All Books");
        model.addAttribute("books", bookRepository.findAll());
        return "customer/index";
    }

    @GetMapping("buybook")
    public String displayBuyBookForm(Model model) {
        model.addAttribute("title", "Buy Books");
        model.addAttribute("books", bookRepository.findAll());
        return "customer/buybook";
    }

    @PostMapping("buybook")
    public String processBuyBookForm(@RequestParam(required = false) int[] bookIds) {
        if(bookIds != null) {
            for (int id : bookIds) {
                Optional<Book> newBook = bookRepository.findById(id);
                if (newBook.isPresent()) {
                    if (newBook.get().getQuantity() > 1) {
                        bookRepository.decrementBookQuantity(id);
                    } else {
                        bookRepository.deleteById(id);
                    }
                }
            }
        }

        return "redirect:/home/customer";
    }

}