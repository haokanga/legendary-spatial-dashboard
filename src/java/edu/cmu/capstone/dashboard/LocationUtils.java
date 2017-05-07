/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.capstone.dashboard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 * Location Analytic.
 *
 * @author Marshall An <marshall.an at cloudcomputing.scs.cmu.edu>
 */
public class LocationUtils {

    public static int getSumVisits(final Location[] locations) {
        if (locations == null) {
            return 0;
        }
        int sum = 0;
        for (Location location : locations) {
            sum += location.getCount();
        }
        return sum;
    }

    public static int getStartVisits(final Location[] locations){
        return locations[0].getCount();
    }
    
    public static int getDstVisits(final Location[] locations){
        return locations[locations.length - 1].getCount();
    }
    
    public static String getLocationNames(final Location[] locations) {
        JSONArray result = new JSONArray();
        for (Location location : locations) {
            result.put(location.getName());
        }
        return result.toString();
    }
    
    public static String getLocationsToTravel(final Location[] locations) {
        JSONArray result = new JSONArray();
        for (int i = 1; i < locations.length; i ++) {
            result.put("To " + locations[i].getName());
        }
        return result.toString();
    }

    public static String getLocationVisits(final Location[] locations) {
        JSONArray result = new JSONArray();
        for (Location location : locations) {
            result.put(location.getCount());
        }
        return result.toString();
    }

    public static String getAvgTimeToTravel(final Location[] locations) {
        JSONArray result = new JSONArray();
        for (int loc = 1; loc < locations.length; loc++) {
            JSONArray prevTimestamps = locations[loc - 1].getTimestamps();
            JSONArray nextTimestamps = locations[loc].getTimestamps();
            long sum = 0;
            DateFormat format = new SimpleDateFormat("E, MMMMM DD, yyyy HH:mm:ss a", Locale.ENGLISH);
            for (int j = 0; j < nextTimestamps.length(); j++) {
                try {
                    long next = format.parse((String) nextTimestamps.get(j)).getTime();
                    long prev = format.parse((String) prevTimestamps.get(j)).getTime();
                    long diffInSec = (next - prev) / 1000;
                    sum += diffInSec;
                } catch (ParseException ex) {
                    Logger.getLogger(LocationUtils.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // avg diffInMin = diffInSec / len * 60
            result.put((double) sum / (nextTimestamps.length() * 60));
        }
        return result.toString();
    }

    public static double getAvgTimeToFinishTour(final Location[] locations) {
        JSONArray startTimestamps = locations[0].getTimestamps();
        JSONArray finishTimestamps = locations[locations.length - 1].getTimestamps();
        long sum = 0;
        DateFormat format = new SimpleDateFormat("E, MMMMM DD, yyyy HH:mm:ss a", Locale.ENGLISH);
        for (int j = 0; j < finishTimestamps.length(); j++) {
            try {
                long finish = format.parse((String) finishTimestamps.get(j)).getTime();
                long start = format.parse((String) startTimestamps.get(j)).getTime();
                long diffInSec = (finish - start) / 1000;
                sum += diffInSec;
            } catch (ParseException ex) {
                Logger.getLogger(LocationUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // avg diffInMin = diffInSec / len * 60
        return (double) sum / (finishTimestamps.length() * 60);

    }

    public static String getLocationRandom(final Location[] locations) {
        JSONArray result = new JSONArray();
        Random random = new Random();
        for (Location location : locations) {
            result.put(1 + random.nextInt(20) * random.nextFloat());
        }
        return result.toString();
    }

    public static String getAvgVisits(final Location[] locations) {
        if (locations == null || locations.length == 0) {
            return "0";
        }
        return String.format("%.1f",
                (float) getSumVisits(locations) / locations.length);
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
