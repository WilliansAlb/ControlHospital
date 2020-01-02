/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.AdministracionDTO;
import POJOS.Areas;
import POJOS.Empleos;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yelbetto
 */
public class Admin extends HttpServlet {

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
            out.println("<title>Servlet Admin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
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
        AdministracionDTO admin = new AdministracionDTO();

        Gson g = new Gson();
        String tipo2 = request.getParameter("tipo2");
        if (tipo2 != null) {
            if (tipo2.equals("1")) {
                Empleos[] cadena;
                cadena = admin.devolviendo();
                ArrayList<String> datos1 = new ArrayList<>();
                for (int i = 0; i < cadena.length; i++) {
                    datos1.add(cadena[i].getId_empleos()+"");
                    datos1.add(cadena[i].getNombre_empleo());
                    datos1.add(cadena[i].getSueldo() + "");
                    if (cadena[i].isDescuentos()) {
                        datos1.add("Sí");
                    } else {
                        datos1.add("No");
                    }
                    datos1.add(cadena[i].getTotal() + "");
                }
                String[] datos2 = new String[datos1.size()];
                for (int u = 0; u < datos1.size(); u++) {
                    datos2[u] = datos1.get(u);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String nuevo;
                nuevo = g.toJson(datos2);
                response.getWriter().write(nuevo);
            } else if (tipo2.equals("2")) {
                Areas[] cadena;
                cadena = admin.devolviendoAreas();
                ArrayList<String> datos1 = new ArrayList<>();
                for (int i = 0; i < cadena.length; i++) {
                    datos1.add(cadena[i].getId_area()+"");
                    datos1.add(cadena[i].getNombre_area());
                    if (cadena[i].isContratando()) {
                        datos1.add("Sí");
                    } else {
                        datos1.add("No");
                    }
                    datos1.add(cadena[i].getTotal() + "");
                    datos1.add(cadena[i].getCandidatos() + "");
                }
                String[] datos2 = new String[datos1.size()];
                for (int u = 0; u < datos1.size(); u++) {
                    datos2[u] = datos1.get(u);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                String nuevo;
                nuevo = g.toJson(datos2);
                response.getWriter().write(nuevo);
            }
        }
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
        AdministracionDTO admin = new AdministracionDTO();
        String tipo = request.getParameter("tipo");
        if (tipo != null) {
            if (tipo.equals("1")) {
                String mensaje = "";
                String nombre_empleo = request.getParameter("nombreempleo");
                Double salario = Double.parseDouble(request.getParameter("salario"));
                int descuentos = Integer.parseInt(request.getParameter("descuentos"));
                admin.ingresarEmpleo(nombre_empleo, salario, descuentos == 1);
                if (admin.isTodoBien()) {
                    mensaje = "Creado empleo";
                } else {
                    mensaje = "Error empleo";
                    admin.setTodoBien(true);
                }
                response.getWriter().write(mensaje);
            } else if (tipo.equals("2")) {
                String mensaje = "";
                String nombre_area = request.getParameter("nombrearea");
                boolean contratando = request.getParameter("contratando").equals("true");
                admin.ingresarArea(nombre_area, contratando);
                if (admin.isTodoBien()) {
                    mensaje = "Creada area";
                } else {
                    mensaje = "Error al crear area";
                }
                response.getWriter().write(mensaje);
            }
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
