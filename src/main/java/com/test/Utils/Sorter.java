package com.test.Utils;


import com.test.models.HotelData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Chaklader on 11/20/16.
 */
public class Sorter {

    public static void sortDataList(int sortValue, List<HotelData> rows) {

        switch (sortValue) {

            // sort based on the name
            case 1:
                rows.sort(Comparator.comparing(HotelData::getName));
//                Collections.sort(rows, new Comparator<HotelData>() {
//                    @Override
//                    public int compare(HotelData p1, HotelData p2) {
//                        return p1.getName().compareTo(p2.getName()); // Ascending
//                    }
//                });
                break;

            // sort based on the hotel rating
            case 2:
                rows.sort(Comparator.comparing(HotelData::getStars).reversed());
//                rows.sort(Comparator.comparing(HotelData::getStars)); // hotel rating based on ascening order

//                Collections.sort(rows, new Comparator<HotelData>() {
//                    @Override
//                    public int compare(HotelData p1, HotelData p2) {
////                        return p1.getStars().compareTo(p2.getStars()); // Ascending
//                        return p2.getStars().compareTo(p1.getStars()); // Descending
//                    }
//                });
                break;

            default:
                System.out.println("SORTING TYPE NOT SUPPORTED");
        }
    }

}
