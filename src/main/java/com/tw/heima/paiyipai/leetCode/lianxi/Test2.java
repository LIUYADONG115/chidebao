package com.tw.heima.paiyipai.leetCode.lianxi;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {
    public static void main(String[] args) throws FileNotFoundException {
        //100MB

        //需要将文件拆分成多个100MB的小文件，对每个文件使用归并排序，在将所有的小文件进行归并，最终得到有效的文件
        String inputName = "oldData.txt";
        String outPutName = "newData.txt";

        int[] buffer = new int[100000];

        List<String> tempFiles = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputName));
            String line;
            int currentLine = 0;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                buffer[currentLine++] = num;

                //当buffer满了以后进行排序，然后写入temp文件
                if (currentLine == 100000) {
                    Arrays.sort(buffer);
                    String tempName = "temp.txt";
                    //写入文件
                    BufferedWriter bw = new BufferedWriter(new FileWriter(tempName));
                    for (int i = 0; i < currentLine; i++) {
                        bw.write(Integer.toString(buffer[i]));
                        bw.newLine();
                    }
                    tempFiles.add(tempName);
                    currentLine = 0;
                }
            }

            //使用归并合并所有的临时文件，怎么搞呢？

        } catch (Exception e) {
            System.out.println("出错了");
        }
    }

    public static void mergeDiv(List<String> tempFiles, String outFile) throws FileNotFoundException {
        List<BufferedReader> readers = new ArrayList<>();
        for (String fileName : tempFiles) {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            readers.add(br);
        }


    }
}
