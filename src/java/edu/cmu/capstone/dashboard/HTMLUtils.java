/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.capstone.dashboard;

import org.json.JSONArray;

/**
 *
 * @author Marshall An <marshall.an at cloudcomputing.scs.cmu.edu>
 */
public class HTMLUtils {
    
    public static String toHtml(final Location[] locations) {
        StringBuilder sb = new StringBuilder();
        for (Location location : locations) {
            sb.append(toHtml(location));
        }
        return sb.toString();
    }
    
    public static String toHtml(final String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\">");
        sb.append(toTableRow(str));
        sb.append("</table><br>");
        return sb.toString();
    }
    
    public static String toHtml(final int str) {
        return toHtml(String.valueOf(str));
    }

    /**
     * Get HTML representation of a location.
     *
     * @param location to show
     * @return the HTML representation
     */
    public static String toHtml(final Location location) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\">");
        sb.append(toTableRow("Name", location.getName()));
        sb.append(toTableRow("Longitude", location.getLongitude()));
        sb.append(toTableRow("Latitude", location.getLatitude()));
        sb.append(toTableRow("Description", location.getDescription()));
        sb.append(toTableRow("ImageUrl", location.getImageUrl()));
        sb.append(toTableRow("Index", location.getIndex()));
        sb.append(toTableRow("Count", location.getCount()));
        sb.append(toTableRow("Timestamps", location.getTimestamps()));
        sb.append("</table><br>");
        return sb.toString();
    }

    /**
     * Return a table row.
     *
     * @param key
     * @param val
     * @return
     */
    private static String toTableRow(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><td>").append(str).append("</td></tr>");
        return sb.toString();
    }

    /**
     * Return a table row given kv pair.
     *
     * @param key
     * @param val
     * @return
     */
    private static String toTableRow(String key, String val) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><td>").append(key).append("</td><td>")
                .append(val.replaceAll("\n", "<br>")).append("</td></tr>");
        return sb.toString();
    }
    
    private static String toTableRow(String key, double val) {
        return toTableRow(key, String.valueOf(val));
    }
    
    private static String toTableRow(String key, int val) {
        return toTableRow(key, String.valueOf(val));
    }
    
    private static String toTableRow(String key, JSONArray val) {
        return toTableRow(key, String.valueOf(val.toString()));
    }
}
