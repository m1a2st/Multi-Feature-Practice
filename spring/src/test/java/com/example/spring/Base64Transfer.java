package com.example.spring;

import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Base64Transfer {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Ken.Huang\\Desktop\\note.png";
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        System.out.println(encodedString);
        String outputFileName = "test.png";
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
    }
}
