package org.educational.libraryapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.repository.AuthorRepository;
import org.educational.libraryapp.repository.BookRepository;
import org.educational.libraryapp.service.LiterAluraServices;
import org.educational.libraryapp.service.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Scanner;

@SpringBootApplication
public class LibraryappApplication implements CommandLineRunner {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	/*	Principal principal = new Principal(bookRepository, authorRepository );
		principal.showMenu();*/

	}
}
