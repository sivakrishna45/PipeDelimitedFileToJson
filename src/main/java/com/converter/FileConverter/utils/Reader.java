package com.converter.FileConverter.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Reader {

    public List<String> readData(String fileName, String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "/" + fileName));
        List<String> data = new ArrayList<>();
        String line = "";
        while ((line = reader.readLine()) != null) {
            data.add(line);
        }
        reader.close();
        return data;
    }
}
