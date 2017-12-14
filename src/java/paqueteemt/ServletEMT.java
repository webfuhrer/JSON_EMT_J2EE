/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteemt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author luis
 */
@WebServlet(name = "ServletEMT", urlPatterns = {"/ServletEMT"})
public class ServletEMT extends HttpServlet {

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
        String idStop=request.getParameter("id_parada");
        String idClient="WEB.SERV.ataraxa@hotmail.com";
        String passKey="83D88CD0-8A9B-4CE6-B976-B922B61FAE6D";
        PeticionPost peticion=new PeticionPost("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetArriveStop.php");
        peticion.add("idClient", idClient);
        peticion.add("passKey", passKey);
        peticion.add("idStop", idStop);
        String respuesta=peticion.getRespueta();
        List<Autobus> lista_autobuses=tratarJSON(respuesta);
        request.setAttribute("lista", lista_autobuses);
        request.getRequestDispatcher("muestradatos.jsp").forward(request, response);
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
        processRequest(request, response);
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

    private List<Autobus> tratarJSON(String json_string) {
        List<Autobus> lista_buses=new ArrayList<>();
        JSONParser parseador=new JSONParser();
        JSONObject objeto_json;
        try {
            objeto_json = (JSONObject)parseador.parse(json_string);
        
        JSONArray obj_lista_paradas=(JSONArray) objeto_json.get("arrives");
                for (int i=0; i<obj_lista_paradas.size();i++)
                {
                    JSONObject objeto_llegada=(JSONObject)obj_lista_paradas.get(i);
                    String lineId=(String)objeto_llegada.get("lineId");
                    String isHead=(String)objeto_llegada.get("isHead");
                    String destination=(String)objeto_llegada.get("destination");
                    long busTimeLeft=(long)objeto_llegada.get("busTimeLeft");
                    Autobus bus=new Autobus(lineId, isHead, destination, busTimeLeft);
                    lista_buses.add(bus);

                }
        } catch (ParseException ex) {
            Logger.getLogger(ServletEMT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista_buses;
    }

}
