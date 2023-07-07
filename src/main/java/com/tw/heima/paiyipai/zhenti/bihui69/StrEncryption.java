package com.tw.heima.paiyipai.zhenti.bihui69;

import java.util.Scanner;

/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-04 8:25 下午
 */
public class StrEncryption {
    /**
     * 字符串加密
     * <p>
     * 给出原文字符串str，通过对字符串的每个字母进行改变来实现加密，
     * 加密方式是在每一个字母str[i]偏移特定数组元素a[i]的量。数组a的前三位已经赋值：a[0]=1,a[1]=2,a[2]=4,
     * 当i>=3时，数组元素a[i]=a[i-1]+a[i-2]+a[i-3]。
     * 例如：原文abcde加密后bdgkr，其中偏移量分别是1，2，4，7，13。
     * <p>
     * 输入描述：第一行是整数n，表示n组测试数据。每组数据包含一行，原文str(只含有小写字母，长度大于0小于50)。
     * 输出描述：每组测试数据输出一行，表示密文。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < str.length(); j++) {
                int currentNum = str.charAt(j) - 'a';
                int pianyiNum = getPainYi(j);
                int mubiaoNum = 0;
                if (currentNum + pianyiNum < 26) {
                    mubiaoNum = currentNum + pianyiNum;
                } else {
                    mubiaoNum = currentNum + pianyiNum % 26;
                }
                char mubiaoChar = (char)('a'+mubiaoNum);
                sb.append(mubiaoChar);
            }
            System.out.println(sb.toString());
        }
    }

    public static int getPainYi(int num) {
        if (num == 0) {
            return 1;
        } else if (num == 1) {
            return 2;
        } else if (num == 2) {
            return 4;
        } else {
            return getPainYi(num - 1) + getPainYi(num - 2) + getPainYi(num - 3);
        }
    }
}
