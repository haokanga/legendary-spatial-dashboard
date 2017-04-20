/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.capstone.dashboard;

/**
 * Location Analytic.
 *
 * @author Marshall An <marshall.an at cloudcomputing.scs.cmu.edu>
 */
public class LocationUtils {

    public static int getVisits(final Location[] locations) {
        if (locations == null) {
            return 0;
        }
        int sum = 0;
        for (Location location : locations) {
            sum += location.getCount();
        }
        return sum;
    }

    public static String getAvgVisits(final Location[] locations) {
        if (locations == null || locations.length == 0) {
            return "0";
        }
        return String.format("%.1f",
                (float) getVisits(locations) / locations.length);
    }

    public static String getMostPopularLocation(final Location[] locations) {
        if (locations == null || locations.length == 0) {
            return "";
        }
        Location res = null;
        int max = 0;
        for (Location location : locations) {
            if (location.getCount() > max) {
                res = location;
                max = location.getCount();
            }
        }
        return res.getName();
    }
}