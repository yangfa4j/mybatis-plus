package com.yf.print;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2021/7/8
 * @Author yangfa
 * @Description
 */
public class PrintTest {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("https://www.cnblogs.com/wenhuang/p/9891199.html");
        System.out.println("url.getPath() = " + url.getPath());


    }





    public static boolean isNeedInsertUrl(String url) {
        Pattern p1 = Pattern.compile("(hfjy+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m1 = p1.matcher(url);
        Pattern p2 = Pattern.compile("(aliyun+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m2 = p2.matcher(url);
        Pattern p3 = Pattern.compile("(qiniu+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m3 = p3.matcher(url);
        Pattern p4 = Pattern.compile("(qnssl+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m4 = p4.matcher(url);
        Pattern p5 = Pattern.compile("(speiyou+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m5 = p5.matcher(url);
        Pattern p6 = Pattern.compile("(haibian+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m6 = p6.matcher(url);
        Pattern p7 = Pattern.compile("(izhikang+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m7 = p7.matcher(url);
        Pattern p8 = Pattern.compile("(fbcontent+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m8 = p8.matcher(url);
        Pattern p9 = Pattern.compile("(mofangge+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m9 = p9.matcher(url);
        Pattern p10 = Pattern.compile("(jyeoo+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m10 = p10.matcher(url);
        Pattern p11 = Pattern.compile("(img+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m11 = p11.matcher(url);
        Pattern p12 = Pattern.compile("(ddyingyu+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m12 = p12.matcher(url);
        Pattern p13 = Pattern.compile("(pic+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m13 = p13.matcher(url);
        Pattern p14 = Pattern.compile("(cdn+)([0-9a-z.]+)(:[0-9]+)?");
        Matcher m14 = p14.matcher(url);
        return m1.find() || m2.find() || m3.find() || m4.find() || m5.find() || m6.find() || m7.find() || m8.find() || m9.find() || m10.find() || m11.find() ||
                m12.find() || m13.find() || m14.find();
    }
}
