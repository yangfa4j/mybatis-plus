package com.qingqing.live_4_6_0;

import com.alibaba.fastjson.JSONObject;
import com.hwl.api.gateway.sdk.Client;
import com.hwl.api.gateway.sdk.http.HttpRequest;
import com.hwl.api.gateway.sdk.http.HttpRequestBuilder;
import com.hwl.api.gateway.sdk.http.HttpResponse;
import com.hwl.api.gateway.sdk.utils.ParameterHelper;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.Arrays;
 
/**
 * @author tal
 * @date 2020/5/7 18:59
 */
public class ClientTest {
 
  private static String appkey = "xxx";
 
  private static String appSecret = "xxx";
 
  private String gatewayHost = "https://apigateway-dev.jiaoyanyun.com";
 
  @Test
  public void get() throws Exception {
    Client client = new Client(appkey, appSecret);
    String originPath = "/themis/ping";
    String url = gatewayHost + originPath;
    byte[] extraByte = ParameterHelper.getBytesUtf8(getExtraDataJS().toJSONString());
    String extraBase64 = Base64.encodeBase64String(extraByte);
 
    HttpRequest request = HttpRequestBuilder.create().GET()
        .setUrl(url)
        .putQuery("extraData", extraBase64).build();
 
    HttpResponse response = client.execute(request);
    System.out.println("------------get request>" + url);
    System.out.println("(Response)HttpCode->"+ response.getStatusCode());
    System.out.println("(Response)ResponseBody->"+ response.getContent());
    System.out.println("(Response)Headers->"+ response.getHeaders());
 
  }
 
 
  @Test
  public void postJson() throws Exception {
    Client client = new Client(appkey, appSecret);
    String originPath = "/halfscreen/index/queryStdHsIndexUrl";
    String url = gatewayHost + originPath;
    byte[] extraByte = ParameterHelper.getBytesUtf8(getExtraDataJS().toJSONString());
    String extraBase64 = Base64.encodeBase64String(extraByte);
 
    String requestBody = getHalfscreenRequestBodyJson().toJSONString();
 
    HttpRequest request = HttpRequestBuilder.create().POST_JSON()
        .setUrl(url)
        .putQuery("extraData", extraBase64)
        .setContent(requestBody)
        .build();
 
    HttpResponse response = client.execute(request);
    System.out.println("------------postJson request>" + url);
    System.out.println("(Response)HttpCode->"+ response.getStatusCode());
    System.out.println("(Response)ResponseBody->"+ response.getContent());
    System.out.println("(Response)Headers->"+ response.getHeaders());
  }
 
  
  @Test
  public void postForm() throws Exception {
    Client client = new Client(appkey, appSecret);
    String originPath = "/themis/ping";
    String url = gatewayHost + originPath;
    byte[] extraByte = ParameterHelper.getBytesUtf8(getExtraDataJS().toJSONString());
    String extraBase64 = Base64.encodeBase64String(extraByte);
 
    HttpRequest request = HttpRequestBuilder.create().POST_FORM()
        .setUrl(url)
        .putQuery("extraData", extraBase64)
        .putBody("uid", "123")
        .putBody("name", "zhang三")
        .build();
 
    HttpResponse response = client.execute(request);
    System.out.println("------------postForm request>" + url);
    System.out.println("(Response)HttpCode->"+ response.getStatusCode());
    System.out.println("(Response)ResponseBody->"+ response.getContent());
    System.out.println("(Response)Headers->"+ response.getHeaders());
  }
 
 
 
  private JSONObject getHalfscreenRequestBodyJson() {
    JSONObject json = new JSONObject();
    json.put("organizationCode", "peiyou");
    json.put("sceneCode", "shuangshitixiaoxitong");
    json.put("identityCode", "banpingfudao");
    json.put("id", "370c7e68b90147edb9f6cb65820be5ed");
    json.put("name", "吴鹏飞");
    json.put("token", "111");
    json.put("logicQuesTypeIdList", Arrays.asList("1", "2"));
    json.put("subjectId", "1");
    json.put("gradeGroupId", "1");
    json.put("subjectIdList", Arrays.asList("1", "2"));
    json.put("gradeGroupIdList", Arrays.asList("1", "2"));
    json.put("groupCode", "123");
    return json;
  }
 
  public static JSONObject getExtraDataJS() {
    JSONObject dataJS = new JSONObject();
    JSONObject userJs = new JSONObject();
    userJs.put("roleCode", "1");
    userJs.put("userId", "10086");
    userJs.put("userName", "张三");
    dataJS.put("user", userJs);
    return dataJS;
  }
 
}