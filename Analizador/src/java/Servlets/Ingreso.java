/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Clases.Guardar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;

/**
 *
 * @author yelbetto
 */
@WebServlet(name = "Ingreso", urlPatterns = {"/Ingreso"})
@MultipartConfig(maxFileSize = 16177215)
public class Ingreso extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ingreso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + request.getParameter("dato") + "</h1>");
            out.println("<h1>Servlet Ingreso at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = "";
        int lineas = 0;
        ArrayList<String> lineas1 = new ArrayList<>();
        if (request.getPart("archivo") != null) {
            InputStream inputStream = null;
            try {
                Part filePart = request.getPart("archivo");
                if (filePart.getSize() > 0) {
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("fichero: " + ex.getMessage());
            }
            try {
                StringBuffer codeBuffered = new StringBuffer();
               
                InputStream in = inputStream;
                BufferedReader read = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = read.readLine()) != null) {
                    //codeBuffered.append(line).append("\n");
                    lineas1.add(line);
                }
                //code = codeBuffered.toString(); // Este es el código de la página :)
                in.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String[] lineando = new String[lineas1.size()];
        
        for (int u = 0; u < lineas1.size(); u++){
            lineando[u] = lineas1.get(u);
        }
        
        HttpSession s = request.getSession();
        Guardar guardando = new Guardar();
        guardando.setLineando(lineando);
        s.setAttribute("usando", guardando);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ingreso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<textarea>");
            for (int i=0; i<lineas1.size();i++){
            out.println(lineas1.get(i));
            }
            out.println("</textarea>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
