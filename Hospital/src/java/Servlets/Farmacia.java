/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Base.FarmaciaDTO;
import com.google.gson.Gson;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yelbetto
 */
public class Farmacia extends HttpServlet {

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
            out.println("<title>Servlet Farmacia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Farmacia at " + request.getContextPath() + "</h1>");
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
        FarmaciaDTO farmacia = new FarmaciaDTO();
        if (request.getParameter("txt1") != null) {
            String nit = request.getParameter("txt1");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String[] precio;
            String nuevo = "";
            if (!nit.isEmpty()) {
                precio = farmacia.datosComprador(nit);
                nuevo = g.toJson(precio);
                log("pasa por aca");
            }
            log(nuevo);
            response.getWriter().write(nuevo);
        } else {
            if (request.getParameter("txt") != null) {

                String precio = "";
                String texto = request.getParameter("txt");
                response.setContentType("text/plain");
                if (farmacia.existencia(texto)) {
                    precio = "1";
                } else {
                    precio = "0";
                }
                log("si llega" + precio);
                PrintWriter out = response.getWriter();
                out.print(precio);
            } else {
                if (request.getParameter("fac") != null) {
                    response.setContentType("text/plain");
                    String fullname = request.getParameter("fac");
                    String precio = farmacia.costou(Integer.parseInt(fullname));
                    PrintWriter out = response.getWriter();
                    out.print(precio);
                } else {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    String id = request.getParameter("id2");
                    String[] precio;
                    String nuevo = "";
                    if (!id.isEmpty() && !id.equalsIgnoreCase("todo")) {
                        precio = farmacia.precio(Integer.parseInt(id));
                        nuevo = g.toJson(precio);
                        log("pasa por aca");
                    }
                    log(nuevo);
                    response.getWriter().write(nuevo);
                }
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
        HttpSession seas = request.getSession();
        FarmaciaDTO farmacia1 = new FarmaciaDTO();
        String id_medicamento = request.getParameter("nombrem");
        if (request.getParameter("venta1") == null) {
            if (id_medicamento.equalsIgnoreCase("todo")) {
                String nombre_medicamento = request.getParameter("nombremn");
                double costo = Double.parseDouble(request.getParameter("costom"));
                int existencias = Integer.parseInt(request.getParameter("cantidadm"));
                int minimom = Integer.parseInt(request.getParameter("minimom"));
                double costototalm = Double.parseDouble(request.getParameter("costototalm"));
                String fecha_ingreso = request.getParameter("start");
                
                farmacia1.agregarMedicamento(nombre_medicamento, "10 gramos", costo, existencias, minimom, fecha_ingreso);
                
                int id = farmacia1.id_medicamento(nombre_medicamento);
                
                farmacia1.agregarCompra(existencias, id, fecha_ingreso, costototalm, seas.getAttribute("usuario").toString());
                seas.setAttribute("mensaje", "Agregado correctamente el medicamento " + nombre_medicamento + ", numero de identificacion del medicamento " + id + " y numero de identificacion de compra 1");
                response.sendRedirect("views/farmacia_1.jsp");
            } else {
                double costo = Double.parseDouble(request.getParameter("costom"));
                int existencias = Integer.parseInt(request.getParameter("cantidadm"));
                int minimom = Integer.parseInt(request.getParameter("minimom"));
                double costototalm = Double.parseDouble(request.getParameter("costototalm"));
                String fecha_ingreso = request.getParameter("start");
                
                farmacia1.agregarCompra(existencias, Integer.parseInt(id_medicamento), fecha_ingreso, costototalm, seas.getAttribute("usuario").toString());
                farmacia1.suplirMedicamento(Integer.parseInt(id_medicamento), "", costo, existencias, minimom, fecha_ingreso);
                response.sendRedirect("views/farmacia.jsp");
            }
        } else {

            String nit = request.getParameter("nit");
            Double total = Double.parseDouble(request.getParameter("totalvent"));
            int id_medicamentov = Integer.parseInt(request.getParameter("codigom"));
            int cantidad = Integer.parseInt(request.getParameter("cantidadunid"));
            String namecl = request.getParameter("namecl");
            String ciudad = request.getParameter("ciu");
            String fecha  = request.getParameter("fechav");
            if (farmacia1.existeCliente(nit)) {
                farmacia1.venderMedicamento(id_medicamentov, "yelbetto", total, cantidad, nit, fecha);
            } else {
                farmacia1.crearCliente(nit, namecl, ciudad);
                farmacia1.venderMedicamento(id_medicamentov, "yelbetto", total, cantidad, nit, fecha);
            }
            response.setContentType("text/plain");
            seas.setAttribute("mensaje", "Todo correcto");
            farmacia1.prueba(total + " " + nit);
            PrintWriter out = response.getWriter();
            out.print(total + "  " +nit);
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
