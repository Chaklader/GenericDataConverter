package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.test.Interfaces.FileReadable;
import com.test.Interfaces.JsonFileConvertable;
import com.test.Interfaces.XmlFileConvertable;
import com.test.Utils.FileFinder;
import com.test.models.Headers;
import com.test.models.HotelData;

import static com.test.Utils.Formatter.parseLine;
import static com.test.Utils.Validator.isNameIsUTF8;
import static com.test.Utils.Validator.isUrlValidated;
import static com.test.Utils.Validator.isValidHotelRating;


/**
 * Created by Chaklader on 11/18/16.
 */
public abstract class FileReader implements FileReadable, 
                                            XmlFileConvertable, 
                                            JsonFileConvertable {

    protected List<HotelData> rows;
    protected HotelData hotelData;
    protected Headers headers;
    private String fileName = null;

    public FileReader(String fileName) {

        this.fileName = fileName;
        this.rows = new ArrayList<>();
        this.hotelData = null;
        this.headers = null;

        fileReader();
    }

    public void fileReader() {

        try {
            readCsvFile();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void readCsvFile() throws FileNotFoundException {

        File file = new FileFinder().getTheFile(fileName);

        try (Scanner scanner = new Scanner(file)) {

            List<String> line = parseLine(scanner.nextLine());

            if (line != null) {

                // name,address,stars,contact,phone,uri
                headers = new Headers(line.get(0),
                        line.get(1),
                        line.get(2),
                        line.get(3),
                        line.get(4),
                        line.get(5));
            }

            while (scanner.hasNext()) {

                line = parseLine(scanner.nextLine());

                if (line != null) {

                    // name,address,stars,contact,phone,uri
                    String name = line.get(0);
                    String address = line.get(1);
                    String stars = line.get(2);
                    String contact = line.get(3);
                    String phone = line.get(4);
                    String uri = line.get(5);

                    boolean nameValidated = isNameIsUTF8(name);
                    boolean urlIsValidated = isUrlValidated(uri);
                    boolean hotelRatingValidated = isValidHotelRating(stars);

                    // name, uri and the hotel rating validated
                    if (nameValidated && urlIsValidated && hotelRatingValidated) {
                        hotelData = new HotelData(name, address, stars, contact, phone, uri);
                        rows.add(hotelData);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
