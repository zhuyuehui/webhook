package com.github.webhook.configuration;

import com.github.webhook.util.HttpClientWrapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Configuration
public class HttpClientConfiguration {
    @Value("${vendor.http.retryCount:3}")
    private int retryCount;
    @Value("${vendor.http.retryInterval:5000}")
    private long retryInterval;
    @Value("${vendor.http.defaultMaxPerRoute:100}")
    private Integer defaultMaxPerRoute;


    @Bean("httpClientWrapper")
    @Primary
    public HttpClientWrapper httpClientWrapper() {
        PoolingHttpClientConnectionManager connectionManager = getHttpClientConnectionManager(defaultMaxPerRoute);
        CloseableHttpClient httpClient = newInstanceUseDefaultRequestConfig(connectionManager);
        return new HttpClientWrapper(httpClient);
    }


    private CloseableHttpClient newInstanceUseDefaultRequestConfig(PoolingHttpClientConnectionManager connectionManager) {
        //ConnectionRequestTimeout 从连接池中获取链接的超时时间
        //ConnectTimeout  连接建立时间，三次握手完成时间
        //SocketTimeout  数据传输过程中数据包之间间隔的最大时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2 * 1000).setConnectTimeout(5 * 1000).setSocketTimeout(20 * 1000).build();

        return HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, false)).
                setConnectionManager(connectionManager).setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(retryCount, retryInterval)).setDefaultRequestConfig(requestConfig)
                .build();

    }


    private PoolingHttpClientConnectionManager getHttpClientConnectionManager(int maxPerRoute) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxPerRoute);
        cm.setDefaultMaxPerRoute(maxPerRoute);
        return cm;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    private class DefaultServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {
        private Integer retryCount;
        private Long retryInterval;


        @Override
        public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
            int code = response.getStatusLine().getStatusCode();
            return code > HttpStatus.SC_INTERNAL_SERVER_ERROR && executionCount <= this.retryCount;
        }

        @Override
        public long getRetryInterval() {
            return this.retryInterval;
        }
    }
}
