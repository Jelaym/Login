/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bd.cDatos;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OwlVirTech
 */
@WebServlet(name= "ValidaUsuario", urlPatterns= ("/index.html"))
public class ValidaUsuario extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String nombre, password;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        nombre= request.getParameter("NOMBRE");
        password= request.getParameter("PASSWORD");
        
        cDatos conex= new cDatos();
        try{
            conex.conectar();
        }catch(SQLException e){
            System.out.println(e);
        }
        ResultSet resul= null;
        try{
            resul=  conex.consulta("SELECT * FROM usuario WHERE nombre='" + nombre + "' AND contraseña='" + password + "'");
        }catch(SQLException e){
            System.out.println(e);
        }
        PrintWriter out= new PrintWriter(response.getOutputStream());
        
        try{
            int conta= 0;
            while(resul.next()){
                conta++;
                if(resul.getString("nombre").equals(nombre)){
                    out.println("<html>");
                    out.println("<head><title>Respuesta al Formulario del Servlet</title></head>");
                    out.println("<body>");
                    out.println("<p><h1><center> Bienvenido " + nombre + " !!</B> </center></h1></p>");
                    out.println("</body></html>");
                }
            }
            if(conta == 0){
                out.println("<html>");
                out.println("<head><title>Respuesta al Formulario del Servlet</title></head>");
                out.println("<body>");
                out.println("<p><h1><center> Usuario no encontrado. </B> </center></h1></p>");
                out.println("</body></html>");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        out.close();
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
