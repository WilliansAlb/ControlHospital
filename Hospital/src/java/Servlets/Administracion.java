/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Base.AdministracionDTO;
import Base.Conexion;
import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author yelbetto
 */
@MultipartConfig(maxFileSize = 16177215)
public class Administracion extends HttpServlet {

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
        response.setContentType("application/pdf");
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;

        try {
            String id = request.getParameter("idempleado");
            ps = con.getDb().prepareStatement("SELECT curriculum FROM Empleados WHERE id_empleado = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);
            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            response.getOutputStream().write(datosPDF);
            bos.close();
            ps.close();
            rs.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        AdministracionDTO ad = new AdministracionDTO();
        Gson g = new Gson();
        if (request.getParameter("idempleado") != null && request.getParameter("tipo")==null) {
            processRequest(request, response);
        } else {
            int areasp = Integer.parseInt(request.getParameter("area"));
            String[] idnombres = ad.nombresSolicitudes(areasp);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String nuevo;
            nuevo = g.toJson(idnombres);
            response.getWriter().write(nuevo);
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
        if (request.getPart("curriculumar") != null) {
            String nombrecandidato = request.getParameter("nombrecandidato");
            String cui = request.getParameter("cui");
            log(request.getParameter("areas"));
            int areas = Integer.parseInt(request.getParameter("areas"));
            InputStream inputStream = null;
            try {
                Part filePart = request.getPart("curriculumar");
                if (filePart.getSize() > 0) {
                    inputStream = filePart.getInputStream();
                }
            } catch (Exception ex) {
                System.out.println("fichero: " + ex.getMessage());
            }
            admin.agregarSolicitud(cui, nombrecandidato, areas, inputStream);
            response.sendRedirect("views/administracion.jsp");
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
