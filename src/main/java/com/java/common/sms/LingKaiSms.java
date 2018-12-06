package com.java.common.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class LingKaiSms {

    public static void sendMsg(String phone, String sms) throws Exception {
        LingKaiParm p = new LingKaiParm();
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));

        p.setUm("654808");
        p.setPw("gdshcm17@");
        p.setDa(phone);
        p.setDc(15);

        p.setRd(1);

        p.setTf(0);

        p.setRf(2);
        if ((p.getDc() == 15) && (p.getTf() == 2)) {
            sms = URLEncoder.encode(sms, "GBK");
            System.out.println(sms);
            p.setSm(sms);
            System.out.println(sms);
            sendPost(p);
            Get(p);
        } else if ((p.getDc() == 15) && (p.getTf() == 3)) {
            sms = URLEncoder.encode(sms, "UTF-8");
            p.setSm(sms);
            System.out.println(sms);
            sendPost(p);

            Get(p);
        } else if ((p.getDc() == 15) && (p.getTf() == 0)) {
            p.setSm(str2HexStr(sms, "GBK"));

            sendPost(p);

            Get(p);
        } else if (p.getDc() == 0) {
            p.setSm(sms);
            sendPost(p);
            Get(p);
        }
        SelSum(p);
    }

    public static String sendPost(LingKaiParm p) {
        String urlString = "http://101.227.68.32:7891/mt";
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = null;
        String param = "";
        try {
            param = "un=" + p.getUm() + "&pw=" + p.getPw() + "&da=" + p.getDa() + "&sm=" + p.getSm() + "&dc=" + p.getDc() + "&rd=" + p.getRd() + "&tf=" + p.getTf();
            realUrl = new URL(urlString);

            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());

            out.print(param);

            out.flush();

            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String line1 = null;
                result = result + line1;
                System.out.println("调用接口返回的值为：" + result);
                result = result.substring(result.indexOf("=") + 1);
            }
            return result;
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("异常信息为：" + ex.getMessage());
            }
        }
        return result;
    }

    public static String str2HexStr(String input, String charsetName)
            throws UnsupportedEncodingException {
        byte[] buf = input.getBytes(charsetName);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void Get(LingKaiParm p)
            throws Exception {
        String urlString = "http://101.227.68.32:7891/mo?";
        try {
            URL url = new URL(urlString + "un=" + p.getUm() + "&pw=" + p.getPw() + "&rf=" + p.getRf());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(10000);

            con.setRequestMethod("GET");
            InputStream istrem = con.getInputStream();

            BufferedReader in2 = new BufferedReader(new InputStreamReader(istrem, "UTF-8"));

            String y = "";
            while ((y = in2.readLine()) != null) {
                System.out.println("获取返回的报告为：" + y + "\n");
            }
            istrem.close();
            con.disconnect();
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println("异常信息为：" + err);
        }
    }

    public static String SelSum(LingKaiParm p)
            throws Exception {
        String sum = null;
        String urlString = "http://101.227.68.32:7891/bi?";
        try {
            URL url = new URL(urlString + "un=" + p.getUm() + "&pw=" + p.getPw() + "&rf=" + p.getRf());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(10000);

            con.setRequestMethod("GET");
            InputStream istrem = con.getInputStream();

            BufferedReader in2 = new BufferedReader(new InputStreamReader(istrem, "UTF-8"));

            String y = "";
            while ((y = in2.readLine()) != null) {
                sum = y.substring(y.indexOf("l") + 4, y.indexOf("}") - 1);

                System.out.println("短信余额：" + sum + "条\n");
            }
            istrem.close();
            con.disconnect();
            return sum;
        } catch (Exception e) {
            String err = e.getMessage();
            System.out.println("异常信息为：" + err);
        }
        return sum;
    }
}
