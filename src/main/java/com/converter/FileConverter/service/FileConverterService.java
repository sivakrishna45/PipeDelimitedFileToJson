package com.converter.FileConverter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.converter.FileConverter.utils.FileConverterUtil;
import com.converter.FileConverter.utils.Reader;

@Service
public class FileConverterService {

    @Value("${spring.application.input.file.name}")
    private String fileName;

    @Value("${spring.application.input.file.path}")
    private String filePath;

    @Value("${spring.application.input.file.separator}")
    private String fileSeparator;

    @Autowired
    Reader reader;

    public String getData() throws Exception {
        List<String> fileData = new ArrayList<>();
        String res = "";
        fileData = reader.readData(fileName, filePath);
        res = FileConverterUtil.processData(fileData, fileSeparator);
        return res;
    }

}
