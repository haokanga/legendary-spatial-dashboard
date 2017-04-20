package edu.cmu.capstone.dashboard;

import org.json.JSONArray;

/**
 * @author Mengyao Zhang
 */
public class Location {
    private int index;
    private int count;
    private String name;
    private double longitude;
    private double latitude;
    private String description;
    private String imageUrl;
    private String markerId;
    private JSONArray timestamps;

    Location(String n, double lon, double lat, String des, String img, int idx, int cnt, String id, JSONArray t) {
        name = n;
        longitude = lon;
        latitude = lat;
        description = des;
        imageUrl = img;
        index = idx;
        count = cnt;
        markerId = id;
        timestamps = t;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {this.description = description;}
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(int longitude) {this.longitude = longitude; }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(int latitude) {this.latitude = latitude; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl; }
    public int getIndex() {return index;}
    public int getCount() {return count;}
    public String getId() {return markerId; }
    public String getName() {return name; }
    public JSONArray getTimestamps() {return timestamps;}
    public void addCount() {count++; }
    public void addTimestamp(String timestamp) {timestamps.put(timestamp); }

}
