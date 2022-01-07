import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
public class GetData {
    public static void main(String[] args) {
        System.out.println(getWeatherData(getPinYin("西安")));
    }
    //    public static void get() {
//        Timer timer = new Timer();
//        //表示在1秒之后开始执行，并且每2秒执行一次
//        timer.schedule(Test.URLLoader("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline&isappinstalled=0"),
//                1000, 1000 * 60 * 60 * 6);
//        timer.schedule(Test.URLLoader("https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline&isappinstalled=0"),
//                1000, 1000 * 60 * 60 * 6);
//    }
    public static String getPinYin(String data) {
        char[] t1 = data.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        for(int i = 0; i < t0; i++) {
            if(Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                return null;
            }
        }
        return t4;
    }
    public static String getWeather(String place) {
        StringBuilder json = null;
        try {
            json = new StringBuilder();
            String url = "https://api.seniverse.com/v3/weather/daily.json?key=SgZa7vFCqwjYlD7R9&location=" + place + "&language=zh-Hans&unit=c&start=-1&days=5";
            //连接url
            URL urlObject = new URL(url);
            if(urlObject != null) {
                URLConnection uc = urlObject.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) uc;
                //伪造浏览器请求
                httpURLConnection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
                httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
                httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
                httpURLConnection.setRequestProperty("Connection", "keep-alive");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:75.0) Gecko/20100101 Firefox/75.0");
                //读取该url返回的json数据
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String inputLine = null;
                while ((inputLine = in.readLine()) != null) {
                    json.append(inputLine);
                }
                //关闭输入流
                in.close();
                Thread.sleep(1000);
                return json.toString();
            }
        } catch (Exception e) {

        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public static String getWeatherData(String place) {
        String data = getWeather(place);
        Map<String, Object> map = null;
        String result = "";
        if(data == null) {
            return null;
        } else {
            map = getJson(data);
            ArrayList<Map<String, Object>> m = (ArrayList<Map<String, Object>>) map.get("results");
            Map<String, Object> m1 = (Map<String, Object>) m.get(0).get("daily");
            System.out.println(m1);
//            if(m != null) {
//                for(Map<String, Object> m1 : m) {
//
//                }
//            }

//            for(Map<String, Object> m1 : m) {
//
//            }
        }
        return result;
    }
    public static Map<String, Object> getJson(String str_json) {
        Map<String, Object> res = null;
        try {
            Gson gson = new Gson();
            res = gson.fromJson(str_json, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
        }
        return res;
    }
}
