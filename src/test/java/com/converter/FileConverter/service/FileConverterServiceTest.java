package com.converter.FileConverter.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.converter.FileConverter.utils.Reader;

public class FileConverterServiceTest {

        @Mock
        Reader reader;

        @InjectMocks
        FileConverterService fileConverterService;

        @BeforeEach
        public void setup() {
                MockitoAnnotations.openMocks(this); // without this we will get NPE
                ReflectionTestUtils.setField(fileConverterService, "fileName",
                                "");
                ReflectionTestUtils.setField(fileConverterService, "filePath",
                                "");
                ReflectionTestUtils.setField(fileConverterService, "fileSeparator",
                                "||");
        }

        @Test
        @DisplayName("Service getData Test With all loan IDs Present in data")
        void getDataTestLoanIds() throws Exception {

                String[] lis = { "header1||header2", "data11||data12", "data21||data22" };
                List<String> data = Arrays.asList(lis);

                Mockito.when(reader.readData(Mockito.anyString(), Mockito.anyString())).thenReturn(data);
                String jsonData = fileConverterService.getData();

                assertEquals(
                                "[{\"header1\":\"data11\",\"header2\":\"data12\"},{\"header1\":\"data21\",\"header2\":\"data22\"}]",
                                jsonData);
        }

        @Test
        @DisplayName("Service getData Test With some loan IDs Present in data")
        void getDataTestSomeLoanIds() throws Exception {

                String[] lis = { "header1||header2", "data11||data12", "data21||data22", "||data32" };
                List<String> data = Arrays.asList(lis);

                Mockito.when(reader.readData(Mockito.anyString(), Mockito.anyString())).thenReturn(data);
                String jsonData = fileConverterService.getData();

                assertEquals(
                                "[{\"header1\":\"data11\",\"header2\":\"data12\"},{\"header1\":\"data21\",\"header2\":\"data22\"}]",
                                jsonData);
        }

}
