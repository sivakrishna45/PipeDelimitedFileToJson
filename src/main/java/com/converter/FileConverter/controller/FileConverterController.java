package com.converter.FileConverter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.converter.FileConverter.service.FileConverterService;

@RestController
public class FileConverterController {

    public final FileConverterService fileConverterService;

    public FileConverterController(FileConverterService fileConverterService) {
        this.fileConverterService = fileConverterService;
    }

    @GetMapping("/getData")
    public String getData() throws Exception {
        return fileConverterService.getData();
    }
}
