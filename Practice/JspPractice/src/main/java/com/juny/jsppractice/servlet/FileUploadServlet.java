package com.juny.jsppractice.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet("/fileupload")
public class FileUploadServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    String uploadPath = "/Users/jijunhyuk";
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdirs();

    Map<String, String> formFields = new HashMap<>();
    List<Map<String, Object>> uploadedFiles = new ArrayList<>();

    for (Part part : request.getParts()) {

      String fieldName = part.getName();
      String fileName = part.getSubmittedFileName();

      if (fileName != null) {

        String filePath = uploadPath + File.separator + fileName;
        part.write(filePath);

        Map<String, Object> fileInfo = new HashMap<>();
        fileInfo.put("fieldName", fieldName);
        fileInfo.put("originalFileName", fileName);
        fileInfo.put("storedFileName", fileName);
        fileInfo.put("contentType", part.getContentType());
        fileInfo.put("size", part.getSize());

        uploadedFiles.add(fileInfo);
      } else {
        formFields.put(fieldName, request.getParameter(fieldName));
      }
    }

    request.setAttribute("formFields", formFields);
    request.setAttribute("uploadedFiles", uploadedFiles);

    request.getRequestDispatcher("chapter7/fileupload01_process.jsp").forward(request, response);
  }
}