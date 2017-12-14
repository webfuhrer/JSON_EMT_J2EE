/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteemt;

import java.util.List;

/**
 *
 * @author luis
 */
public class CreaHTML {
    public static String crearTabla(List<Autobus> lista_buses)
    {
        String aux="<table>";
        aux+="<tr><th>Línea</th><th>Hacia</th><th>Cabecera</th><th>Falta(min)</th></tr>";
        for (Autobus bus: lista_buses)
        {
            String cabecera=(bus.getIsHead().equals("true"))?"SÍ":"NO";
            int minutos=Math.round(bus.getBusTimeLeft()/60);
            aux+="<tr><td>"+bus.getLineId()+"</td><td>"+bus.getDestination()+"</td><td>"+cabecera+"</td><td>"+minutos+"</td></tr>";
        }
      aux+="</table>";   
      return aux;
    }
}
