package com.test.alicloud;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsRequest;
import com.aliyuncs.vod.model.v20170321.SubmitTranscodeJobsResponse;
import org.apache.commons.codec.binary.Base64;

import java.io.File;

/**
 * @Date 2020/10/28
 * @Author yangfa
 * @Description
 */
public class Upload {

    // 初始化客户端
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    /**
     * imagesvc的第一个需求  获取视频上传地址和凭证 入参：setFileName、
     *
     * @param client 发送请求客户端
     * @return CreateUploadVideoResponse 获取视频上传地址和凭证响应数据
     * @throws Exception
     */
    public static CreateUploadVideoResponse createUploadVideo(DefaultAcsClient client) throws Exception {
        CreateUploadVideoRequest request = new CreateUploadVideoRequest();
        // 这两个参数设置为同一个
        request.setTitle("转码测试.mp4");
        request.setFileName("转码测试.mp4");

        // 腾讯云是设置了文件大小的，在提交上传的时候（前端传，我不用）
        // request.setFileSize();

        //UserData，用户自定义设置参数，用户需要单独回调URL及数据透传时设置(非必须)
        //JSONObject userData = new JSONObject();
        //UserData回调部分设置
        //JSONObject messageCallback = new JSONObject();
        //messageCallback.put("CallbackURL", "http://xxxxx");
        //messageCallback.put("CallbackType", "http");
        //userData.put("MessageCallback", messageCallback.toJSONString());
        //UserData透传数据部分设置
        //JSONObject extend = new JSONObject();
        //extend.put("MyId", "user-defined-id");
        //userData.put("Extend", extend.toJSONString());
        //request.setUserData(userData.toJSONString());
        //设置请求超时时间
        request.setSysReadTimeout(1000);
        request.setSysConnectTimeout(1000);
        return client.getAcsResponse(request);
    }

    // 请求示例
    public static void main(String[] argv) throws Exception {
        DefaultAcsClient client = initVodClient("LTAI4FzZwwtTXFLjZ63FsWdv", "3jRzhPuIgU9eKHXszUcc3VxCOMJWdq");

        GetPlayInfoResponse playInfo = getPlayInfo(client);
        System.out.println("playInfo = " + playInfo);
//        String localFile = "C:\\Users\\yangfa\\Desktop\\测试课件\\101531280ea.mp4";
//        CreateUploadVideoResponse response = new CreateUploadVideoResponse();
//        try {
//            response = createUploadVideo(client);
//            // 执行成功会返回VideoId、UploadAddress和UploadAuth
//            String videoId = response.getVideoId();
//            // 第一步申请上传后的返回给前端的值，需要给四个，+ videoId
//            JSONObject uploadAuth = JSONObject.parseObject(decodeBase64(response.getUploadAuth()));
//            JSONObject uploadAddress = JSONObject.parseObject(decodeBase64(response.getUploadAddress()));
//
//            // 第二部 接收前端再次传给我的值，完成上传，写media表，video表，视频转码表
//            // 使用UploadAuth和UploadAddress初始化OSS客户端
//            OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);
//
//            // 上传文件，注意是同步上传会阻塞等待，耗时与文件大小和网络上行带宽有关
//            uploadLocalFile(ossClient, uploadAddress, localFile);
//            System.out.println("Put local file succeed, VideoId : " + videoId);
//
//
//            SubmitTranscodeJobsResponse submitTranscodeJobsResponse = submitTranscodeJobs(client,videoId);
//            System.out.println("submitTranscodeJobsResponse = " + submitTranscodeJobsResponse);
//
//        } catch (Exception e) {
//            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
//        }
//        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    // 解密
    public static String decodeBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            Base64 decoder = new Base64();
            try {
                b = decoder.decode(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static OSSClient initOssClient(JSONObject uploadAuth, JSONObject uploadAddress) {
        String endpoint = uploadAddress.getString("Endpoint");
        String accessKeyId = uploadAuth.getString("AccessKeyId");
        String accessKeySecret = uploadAuth.getString("AccessKeySecret");
        String securityToken = uploadAuth.getString("SecurityToken");
        return new OSSClient(endpoint, accessKeyId, accessKeySecret, securityToken);
    }

    public static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, String localFile) {
        String bucketName = uploadAddress.getString("Bucket");
        String objectName = uploadAddress.getString("FileName");
        File file = new File(localFile);
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, file);
        System.out.println(putObjectResult.getRequestId());
    }


    /**
     * 提交媒体处理作业 提交转码
     */
    public static SubmitTranscodeJobsResponse submitTranscodeJobs(DefaultAcsClient client, String videoId) throws ClientException {
        SubmitTranscodeJobsRequest request = new SubmitTranscodeJobsRequest();
        //需要转码的视频ID
        request.setVideoId(videoId);
        //转码模板ID
        request.setTemplateGroupId("f7320fa643435e74dabb6ce883ddaa30");
        return client.getAcsResponse(request);
    }

    /**
     * 获取播放地址函数
     */
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("0a72e3933441499caa2f6fbdd7bee122");
        return client.getAcsResponse(request);
    }

}
