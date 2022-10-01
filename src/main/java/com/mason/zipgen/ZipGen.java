/**
 * This mini-program generates the zip file that I upload to the Trollface GitHub page.
 * It is not intended for public use. I just made it because assembling the zip file
 * manually is mildly inconvenient. The program is executed in IntelliJ via a gradle task.
 */

package com.mason.zipgen;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipGen {
    public static void main(String[] args) throws IOException {
        String currentVersion = "trollface-0.2.0-alpha-1.18.2";
        String projectDir = "D:/Programs/Projects/Trollface/";

        String fileJar = projectDir + "build/libs/" + currentVersion + ".jar";
        String fileCredits = projectDir + "CREDITS.txt";
        String fileImportant = projectDir + "IMPORTANT.txt";
        String[] files = {fileJar, fileCredits, fileImportant};

        File zipFile = new File(projectDir + "src/main/resources/zip/" + currentVersion + ".zip");
        if (!zipFile.getParentFile().exists()) {
            zipFile.getParentFile().mkdirs();
        }
        ZipOutputStream output = new ZipOutputStream(new FileOutputStream(zipFile));

        for (int i = 0; i < files.length; i++) {
            File contents = new File(files[i]);
            FileInputStream input = new FileInputStream(contents);
            output.putNextEntry(new ZipEntry(contents.getName()));

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            input.close();
            output.closeEntry();
        }
        
        output.close();
    }
}
