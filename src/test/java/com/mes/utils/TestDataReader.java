package com.mes.utils;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataReader {

    @DataProvider(name = "csvData")
    public static Iterator<Object[]> getLoginData() throws IOException {
        String filePath = "src/test/resources/testdata/login-data.csv"; // đường dẫn tới file CSV
        List<Object[]> testData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // bỏ qua header
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                // Convert the third column (loginStatus) from string to boolean
                boolean loginStatus = Boolean.parseBoolean(data[2]);
                testData.add(new Object[]{data[0], data[1], loginStatus});
            }
        }
        return testData.iterator();
    }
}
