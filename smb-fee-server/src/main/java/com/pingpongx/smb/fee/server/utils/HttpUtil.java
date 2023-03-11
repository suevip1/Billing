package com.pingpongx.smb.fee.server.utils;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author chenbz@pingpongx.com
 * @version 1.0.0
 * @description 描述
 * @time 2019/04/23 14:57
 */
@Slf4j
public class HttpUtil {

    public static String post(Map<String, Object> param, String secret, String url) {
        OkHttpClient client = new OkHttpClient();
        String content = JSON.toJSONString(param);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), content);

        String sign = "";
        if (StringUtils.isNotBlank(secret)) {
            sign = SHAUtil.sha512(content + secret);
        }
        log.info("post请求信息，url = {}, content = {}, sign = {}", url, content, sign);
        Request post = new Request.Builder()
                .url(url)
                .addHeader("sign", sign)
                .post(requestBody)
                .build();

        Call call = client.newCall(post);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("获取汇率对异常", e);
        }
        return null;
    }

    public static String get(Map<String, Object> param, String secret, String url) {
        OkHttpClient client = new OkHttpClient();
        String paramStr = toSortedQueryStr(param);
        String sign = "";
        if (StringUtils.isNotBlank(secret)) {
            sign = SHAUtil.sha512(paramStr + secret);
        }
        log.info("get请求信息，url = {}, content = {}, sign = {}", url, paramStr, sign);
        Request get = new Request.Builder()
                .url(url + "?" + paramStr)
                .addHeader("sign", sign)
                .get()
                .build();

        Call call = client.newCall(get);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            log.error("获取汇率对异常", e);
        }
        return null;
    }

    private static String toSortedQueryStr(Map<String, Object> param) {

        StringBuilder sb = new StringBuilder();

        // 对键进行排序
        List<String> keys = new ArrayList<>(param.keySet());
        Collections.sort(keys);

        // 构建查询字符串
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = param.get(key);
            sb.append(key).append("=").append(value).append("&");
        }

        int end = sb.length();
        if (sb.length() > 0) {
            end -= 1; // 去除最后一个&
        }

        return sb.substring(0, end);
    }

    /**
     * 获取请求体，编码为UTF-8
     *
     * @param request 请求
     * @return 请求体数据
     */
    public static String getBody(HttpServletRequest request) throws IOException {
        return getBody(request, "UTF-8");
    }

    /**
     * 获取请求体
     *
     * @param request 请求
     * @param charset 编码
     * @return 请求体数据
     */
    public static String getBody(HttpServletRequest request, String charset) throws IOException {
        byte[] data = getBodyBytes(request);
        return new String(data, charset);
    }

    /**
     * 获取请求体字节数据
     *
     * @param request 请求
     * @return 请求体字节数据
     */
    public static byte[] getBodyBytes(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inputStream = request.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            byte[] buffer = new byte[512];
            int len;
            while ((len = bis.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } finally {
//            bis.close();
        }
    }

}
