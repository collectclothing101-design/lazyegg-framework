package io.lazyegg.sdk;

import io.lazyegg.sdk.demo.DemoClient;
import io.lazyegg.sdk.demo.DemoSDKOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class HttpClientTest {

    @Test
    public void test() {
// 创建 HttpClient 实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 Get 请求
        HttpGet httpGet = new HttpGet("https://www.baidu.com");

        // 设置超时时间
        int timeout = 5000; // 单位：毫秒
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        httpGet.setConfig(requestConfig);

        // 执行请求
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 处理响应
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);
            // 关闭 HttpClient
            httpClient.close();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void test2() throws URISyntaxException, IOException {
        ClientConfiguration configuration = new ClientConfiguration();
        DefaultServiceClient defaultServiceClient = new DefaultServiceClient(configuration);
        ExecutionContext context = new ExecutionContext();

        RequestMessage request = new RequestMessage(new GenericRequest());
        request.setMethod(HttpMethod.GET);
        request.setEndpoint(new URI("https://www.baidu.com"));
//        request.setResourcePath("/s");
//        request.addParameter("wd", "java");
        ResponseMessage responseMessage = defaultServiceClient.sendRequest(request, context);
        System.out.println(IOUtils.toString(responseMessage.getContent()));
    }

    @Test
    public void test3() {
        DefaultCredentialsProvider credentialsProvider = new DefaultCredentialsProvider("a", "a");
        DemoClient demoClient = new DemoClient("https://www.baidu.com", credentialsProvider, new ClientConfiguration());
        demoClient.demoSDKOperation().getUser();
    }


}
