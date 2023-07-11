package com.tw.heima.paiyipai.zhenti.bihui69.BJuan;

import java.util.Arrays;
import java.util.Scanner;
/**
 * @author yadong.liu
 * @company thoughtworks
 * @create 2023−07-11 12:23 上午
 */
public class Shitanggongchan {

    /**
     * 某公司员工食堂以盒饭方式供餐。
     * 为将员工取餐排队时间降低为0，食堂的供餐速度必须要足够快。
     * 现在需要根据以往员工取餐的统计信息，计算出一个刚好能达成排队时间为0的最低供餐速度。即，食堂在每个单位时间内必须至少做出多少价盒饭才能满足要求。
     *
     * 输入：
     * 第1行为一个正整数N，表示食堂开餐时长。
     * 第2行为一个正整数M，表示开餐前食堂已经准备好的盒饭份数。
     * 第3行为N个正整数，用空格分隔，依次表示开餐时间内按时间顺序每个单位时间进入食堂取餐的人数Pi。
     * 例如：
     * 3
     * 14
     * 10 4 5
     *
     * 输出：
     * 一个整数，能满足题目要求的最低供餐速度（每个单位时间需要做出多少份盒饭）。
     * 3
     *
     * 说明：
     * 本样例中，总共有3批员工就餐，每批人数分别为10、4、5。
     * 开餐前食堂库存14份。
     * 食堂每个单位时间至少要做出3份餐饭才能达成排队时间为0的目标。具体情况如下:
     * 第一个单位时间来的10位员工直接从库存取餐。取餐后库存剩余4份盒饭，加上第一个单位时间做出的3份，库存有7份
     * 第二个单位时间来的4员工从库存的7份中取4份。取餐后库存剩余3份盒饭，加上第二个单位时间做出的3份，库存有6份。
     * 第三个单位时间来的员工从库存的6份中取5份，库存足够。
     * 如果食堂在单位时间只能做出2份餐饭，则情况如下:
     * 第一个单位时间来的10位员工直接从库存取餐。取餐后库存剩余4份盒饭，加上第一个单位时间做出的2份，库存有6份
     * 第二个单位时间来的4员工从库存的6份中取4份。取餐后库存剩余2份盒饭，加上第二个单位时间做出的2份，库存有4份.
     * 第三个单位时间来的员工需要取5份，但库存只有4份，库存不够。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 食堂开餐时长
        int n = sc.nextInt();
        // 开餐前食堂已经准备好的盒饭份数
        int m = sc.nextInt();
        // 开餐时间内按时间顺序每个单位时间进入食堂取餐的人数
        int[] pi = new int[n];
        for (int i = 0; i < n; i++) {
            pi[i] = sc.nextInt();
        }
        // 最低供餐速度
        int ans = 0;
        // 二分查找
        int min = 0;
        int max = Arrays.stream(pi).max().orElse(0);
        while (min <= max) {
            int mid = (min + max) >> 1;
            if (check(m, mid, pi)) {
                ans = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(ans);
    }

    public static boolean check(int m, int add, int[] pi) {
        // 开餐前食堂已经准备好的盒饭份数
        m -= pi[0];
        for (int i = 1; i < pi.length; i++) {
            m += add;
            if (m >= pi[i]) {
                m -= pi[i];
            } else {
                return false;
            }
        }
        return true;
    }
}
