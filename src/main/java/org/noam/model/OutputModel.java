package org.noam.model;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Output class that holds the specific attributes to extract from each record
 */
public class OutputModel {

    @JsonProperty("spins")
    private int spins;

    @JsonProperty("server_time")
    private String serverDate;

    public int getSpins() {
        return spins;
    }

    public String getServerTime() {
        return serverDate;
    }

    @Override
    public String toString() {
        return this.getSpins() + "," + this.getServerTime();
    }
}
