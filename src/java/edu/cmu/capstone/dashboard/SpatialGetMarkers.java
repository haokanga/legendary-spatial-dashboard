package edu.cmu.capstone.dashboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Mengyao Zhang
 * @author Marshall An <marshall.an at cloudcomputing.scs.cmu.edu>
 */
public class SpatialGetMarkers {

    private static final String SPATIAL_URL_ALL = "https://spatial-api-poc.herokuapp.com/v1/markers-by-project";
    private static final String DEFAULT_PROJECT_ID = "58d324e6de36e3001164bca3";

    protected static Location[] getLocations() {
        return parseLocations(getResponse());
    }

    protected static String getResponse() {
        URL url;
        HttpURLConnection urlConnection;
        String response = null;
        try {
            url = new URL(SPATIAL_URL_ALL + "?projectId=" + DEFAULT_PROJECT_ID);
            urlConnection = (HttpURLConnection) url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                response = readStream(urlConnection.getInputStream());
            }
            if (response == null) {
                return null;
            }
            return response;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readStream(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static Location[] parseLocations(String response) throws JSONException {
        if (response == null) {
            return null;
        }
        JSONObject json = new JSONObject(response);
        JSONArray locations = json.getJSONArray("markers");
        Location[] path = new Location[locations.length()];
        for (int i = 0; i < locations.length(); i++) {
            JSONObject jsonLoc = locations.getJSONObject(i);
            String des = jsonLoc.getString("description");
            String n = jsonLoc.getString("name");
            double lon = jsonLoc.getJSONObject("loc").getJSONArray("coordinates").getDouble(0);
            double lat = jsonLoc.getJSONObject("loc").getJSONArray("coordinates").getDouble(1);
            String img = jsonLoc.getJSONObject("metadata").getString("image");
            int index = jsonLoc.getJSONObject("metadata").getInt("index");
            int count = jsonLoc.getJSONObject("metadata").getInt("count");
            JSONArray timestamps;
            if (jsonLoc.getJSONObject("metadata").has("timestamps")) {
                timestamps = jsonLoc.getJSONObject("metadata").getJSONArray("timestamps");
            } else {
                timestamps = new JSONArray();
            }
            String id = jsonLoc.getString("_id");
            path[index] = new Location(n, lon, lat, des, img, index, count, id, timestamps);
        }
        return path;
    }

}
