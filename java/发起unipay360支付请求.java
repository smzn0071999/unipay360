import okhttp3.*;//maven okhttp

import java.io.IOException;

public class 发起unipay360支付请求 {
    public static void main(String[] args) throws Exception {
        //发起unipay360支付请求
        String order_sn = "123";//订单编号
        String price = "0.21";//金额
        String user_phone = "12345678900";//平台注册手机号
        String remark = "订单备注";//订单备注 用于显示在订单上
        String title = "订单标题";//订单标题 用于显示在订单上
        String notify_url = "http://xxxx/notify";//通知回调地址
        String return_url = "http://xxxx";//支付完成返回地址
        String secret = "xxxxxxxxxxx";//用户secret

        Utl utl = new Utl();
        String sign = utl.MD5(order_sn + price + user_phone + title + remark + notify_url + return_url + secret, "UTF-8");
        System.out.println(sign);
        okhttp发送请求(order_sn, price, user_phone, remark, title, notify_url, return_url, secret, sign);

    }

    public static void okhttp发送请求(String order_sn, String price, String user_phone, String remark, String title, String notify_url, String return_url, String secret, String sign) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://unipay360.com/payform/api/query/pre_pay?" + "order_sn=" + order_sn + "&price=" + price + "&user_phone=" + user_phone + "&remark=" + remark + "&title=" + title + "&notify_url=" + notify_url + "&return_url=" + return_url + "&secret=" + secret + "&sign=" + sign)
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
