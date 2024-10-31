package com.juny.jsppractice.servlet;

import com.juny.jsppractice.dao.BookRepository;
import com.juny.jsppractice.dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bookupload")
@MultipartConfig
public class BookUploadServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    String uploadPath = "/Users/jijunhyuk/JunyProjects/Learning/Practice/JspPractice/src/main/webapp/resources/images";
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdirs();

    Map<String, String> formFields = new HashMap<>();
    String fileName = null;

    for (Part part : request.getParts()) {
      String fieldName = part.getName();
      if (part.getSubmittedFileName() != null) {
        fileName = part.getSubmittedFileName();
        String filePath = uploadPath + File.separator + fileName;
        part.write(filePath);
      } else {
        formFields.put(fieldName, request.getParameter(fieldName));
      }
    }

    String bookId = formFields.get("bookId");
    String name = formFields.get("name");
    String unitPrice = formFields.get("unitPrice");
    String author = formFields.get("author");
    String publisher = formFields.get("publisher");
    String releaseDate = formFields.get("releaseDate");
    String description = formFields.get("description");
    String category = formFields.get("category");
    String unitsInStock = formFields.get("unitsInStock");
    String condition = formFields.get("condition");

    int price = unitPrice.isEmpty() ? 0 : Integer.parseInt(unitPrice);
    long stock = unitsInStock.isEmpty() ? 0 : Long.parseLong(unitsInStock);

    Book newBook = new Book();
    newBook.setBookId(bookId);
    newBook.setName(name);
    newBook.setUnitPrice(price);
    newBook.setAuthor(author);
    newBook.setPublisher(publisher);
    newBook.setReleaseDate(releaseDate);
    newBook.setDescription(description);
    newBook.setCategory(category);
    newBook.setUnitsInStock(stock);
    newBook.setCondition(condition);
    newBook.setFilename(fileName);

    BookRepository dao = BookRepository.getInstance();
    dao.addBook(newBook);

    response.sendRedirect("books.jsp");
  }
}
