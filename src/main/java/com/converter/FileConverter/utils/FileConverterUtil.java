package com.converter.FileConverter.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileConverterUtil {

    public static String processData(List<String> data, String separator) throws Exception {
        String[] header = data.get(0).split(Pattern.quote(separator));
        List<String[]> csvData = data.stream()
                .skip(1)
                .filter(line -> !line.startsWith(separator))
                .map(line -> line.split(Pattern.quote(separator)))
                .toList();
        return fromFileToJson(header, csvData);
    }

    private static String fromFileToJson(String[] header, List<String[]> csvData) throws Exception {

        List<Map<String, String>> jsonData = IntStream.range(0, csvData.size())
                .mapToObj(i -> {
                    Map<String, String> fields = IntStream.range(0, header.length)
                            .boxed()
                            .collect(Collectors.toMap(j -> header[j],
                                    j -> csvData.get(i).length > j ? csvData.get(i)[j] : "", (a, b) -> b,
                                    LinkedHashMap::new));
                    return fields;
                })
                .collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonData);
    }
}
