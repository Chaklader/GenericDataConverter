package com.test.models;

import com.test.Interfaces.JSONNable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaklader on 11/19/16.
 */
public class HotelData implements JSONNable {

    private String name;
    private String address;
    private String stars;
    private String contact;
    private String phone;
    private String uri;


    public HotelData(String name, String address, String stars, String contact, String phone, String uri) {
        this.name = name;

        // string.replaceAll("^\"|\"$", "");
        this.address = address.replaceAll("^\"|\"$", "");
        this.stars = stars;
        this.contact = contact;
        this.phone = phone;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public String toJSON() {
        return String.format("{\"name\" : \"%s\", \"address\" : \"%s\", \"stars\" : \"%s\", " +
                "\"contact\" : \"%s\", \"phone\" : \"%s\", \"uri\" : \"%s\"}",
                name, address, stars, contact, phone, uri);
    }

    /* or any other iterable<HotelData> hotels, including an array */
    public static String hotelsToJSON(List<HotelData> hotels) {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        for (HotelData hotel : hotels) {

            sb.append(hotel.toJSON());
            sb.append(',');
        }

        // sb.insert(sb.length() - 1, ']');
        String jsonValue = sb.toString().substring(0, sb.length()-1).concat("]"); 
        return jsonValue;
    }
}
