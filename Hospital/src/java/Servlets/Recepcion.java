/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.ClientesDTO;
import Base.PacientesDTO;
import Base.RecepcionDTO;
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
public class Recepcion extends HttpServlet {

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
            out.println("<title>Servlet Recepcion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Recepcion at " + request.getContextPath() + "</h1>");
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
        Gson g = new Gson();
        RecepcionDTO rep = new RecepcionDTO();
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        if (tipo == 1) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String id_empleado = request.getParameter("txt");
            String hoyFecha = request.getParameter("fechaconsulta");
            String nuevo;
            ArrayList<String> listado = rep.listadoEspera(id_empleado,hoyFecha);
            String[] listadoConsultas = new String[listado.size()];
            for (int i = 0; i < listado.size(); i++){
                listadoConsultas[i] = listado.get(i);
            }
            nuevo = g.toJson(listadoConsultas);
            log("Imprimir listado");
            response.getWriter().write(nuevo);
        } else if (tipo == '2') {

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
        ClientesDTO cli = new ClientesDTO();
        RecepcionDTO rep = new RecepcionDTO();
        PacientesDTO pac = new PacientesDTO();
        HttpServletRequest u = request;
        int tipo = Integer.parseInt(request.getParameter("tipo"));
        String id_empleado = u.getParameter("id_empleado");
        
        if (tipo == 1) {
            String nombre = u.getParameter("nombre");
            String cui = u.getParameter("cui");
            String edad = u.getParameter("edad");
            String peso = u.getParameter("peso");
            String sexo = u.getParameter("sexo");
            String mensaje = "";
            if (rep.existePaciente(nombre)) {
                mensaje = "Existe";
            } else {
                rep.crearPaciente(nombre, Integer.parseInt(edad), Integer.parseInt(peso), cui, sexo);
                mensaje = "Creado 2";
            }
            response.getWriter().write(mensaje);
        } else if (tipo == 2) {
            String nombre = u.getParameter("nombre");
            String fecha = u.getParameter("fecha");
            String nombre_paciente = u.getParameter("nombrePaciente");
            String ciudad = u.getParameter("ciudad");
            Double valor = Double.parseDouble(u.getParameter("valor"));
            String nit = u.getParameter("nit");
            cli.crearCliente(nit, nombre, ciudad);
            int id_paciente = pac.codigoPaciente(nombre_paciente);
            rep.ingresarConsulta(id_paciente,id_empleado, 1, valor, fecha, nit);
            response.getWriter().write("Creado Cliente");
        } else if (tipo == 3) {
            String fecha = u.getParameter("fecha");
            String nombre_paciente = u.getParameter("nombrePaciente");
            Double valor = Double.parseDouble(u.getParameter("valor"));
            String nit = u.getParameter("nit");
            int id_paciente = pac.codigoPaciente(nombre_paciente);
            rep.ingresarConsulta(id_paciente,id_empleado, 1, valor, fecha, nit);
            response.getWriter().write("Creado Cliente");
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
