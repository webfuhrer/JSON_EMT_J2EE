/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteemt;

/**
 *
 * @author luis
 */
public class Autobus {
    private String lineId;
    private String isHead;
    private String destination;
    private long busTimeLeft;

    public Autobus(String lineId, String isHead, String destination, long busTimeLeft) {
        this.lineId = lineId;
        this.isHead = isHead;
        this.destination = destination;
        this.busTimeLeft = busTimeLeft;
    }

    public String getLineId() {
        return lineId;
    }

    public String getIsHead() {
        return isHead;
    }

    public String getDestination() {
        return destination;
    }

    public long getBusTimeLeft() {
        return busTimeLeft;
    }
    
}
