package app.utils;

import app.model.HttpRequestParam;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * http 请求工具类
 */

public class HttpRequestUtils {
    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    public static Map<String, Object> commonHeaders;

    public HttpRequestUtils() {
    }

    public static String getRequest(HttpRequestParam request) {
        String response = doRequest(request, HttpRequestUtils.HttpMethod.GET);
        return response;
    }

    public static String postRequest(HttpRequestParam request) {
        String response = doRequest(request, HttpRequestUtils.HttpMethod.POST);
        return response;
    }

    public static String putRequest(HttpRequestParam request) {
        String response = doRequest(request, HttpRequestUtils.HttpMethod.PUT);
        return response;
    }

    public static String deleteRequest(HttpRequestParam request) {
        String response = doRequest(request, HttpRequestUtils.HttpMethod.DELETE);
        return response;
    }

    private static String doRequest(HttpRequestParam request, HttpRequestUtils.HttpMethod method) {
        RequestConfig requestConfig = createRequestConfig(request);
        URI uri = createRequestUri(request);
        Header[] commonHeaderArr = null;
        if (commonHeaders != null) {
            commonHeaderArr = createHeaders(commonHeaders);
        }

        Header[] requestHeaderArr = null;
        if (request.getHeaders() != null) {
            requestHeaderArr = createHeaders(request.getHeaders());
        }

        HttpRequestBase httpBase = null;
        switch(method) {
            case GET:
                HttpGet httpGet = new HttpGet(uri);
                httpBase = httpGet;
                break;
            case POST:
                HttpPost httpPost = new HttpPost(uri);
                httpPost.setEntity(createHttpEntity(request));
                httpBase = httpPost;
                break;
            case PUT:
                HttpPut httpPut = new HttpPut(uri);
                httpPut.setEntity(createHttpEntity(request));
                httpBase = httpPut;
                break;
            case DELETE:
                HttpDelete httpDelete = new HttpDelete(uri);
                httpBase = httpDelete;
        }

        String result = null;
        if (httpBase != null) {
            ((HttpRequestBase)httpBase).setConfig(requestConfig);
            if (requestHeaderArr != null) {
                ((HttpRequestBase)httpBase).setHeaders(requestHeaderArr);
            }

            if (commonHeaderArr != null) {
                ((HttpRequestBase)httpBase).setHeaders(commonHeaderArr);
            }

            try {
                CloseableHttpResponse response = httpClient.execute((HttpUriRequest)httpBase);
                result = getResponse(response);
            } catch (IOException var14) {
                System.err.print(var14);
            } finally {
                ;
            }
        }

        return result;
    }

    private static RequestConfig createRequestConfig(HttpRequestParam request) {
        int connectionRequestTimeout = request.getConnectionRequestTimeout();
        int connectTimeout = request.getConnectTimeout();
        int socketTimeout = request.getSocketTimeout();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        return requestConfig;
    }

    private static URI createRequestUri(HttpRequestParam request) {
        URI uri = null;

        try {
            String uriStr = request.getUri();
            URIBuilder uriBuilder = new URIBuilder(uriStr);
            Map<String, Object> queryParams = request.getQueryParams();
            if (queryParams != null) {
                List<NameValuePair> nvps = createQueryParam(queryParams);
                uriBuilder.setParameters(nvps);
            }

            Object queryObject = request.getQueryObject();
            if (queryObject != null) {
                Map<String, Object> queryObjectParams = JsonUtils.obj2Map(queryObject);
                List<NameValuePair> nvps = createQueryParam(queryObjectParams);
                uriBuilder.setParameters(nvps);
            }

            uri = uriBuilder.build();
        } catch (URISyntaxException var8) {
            System.err.println(var8);
        }

        return uri;
    }

    private static List<NameValuePair> createQueryParam(Map<String, Object> queryParams) {
        List<NameValuePair> nameValuePairList = new ArrayList();
        if (queryParams != null) {
            Iterator var2 = queryParams.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var2.next();
                Object value = entry.getValue();
                if (null != value) {
                    String valueStr = value.toString();
                    String key = (String)entry.getKey();
                    NameValuePair nameValuePair = new BasicNameValuePair(key, valueStr);
                    nameValuePairList.add(nameValuePair);
                }
            }
        }

        return nameValuePairList;
    }

    private static HttpEntity createHttpEntity(HttpRequestParam request) {
        HttpEntity httpEntity = null;
        Map<String, Object> bodyParam = request.getBodyParams();
        if (bodyParam != null) {
            String bodyParamJson = JsonUtils.obj2json(bodyParam);
            httpEntity = new StringEntity(bodyParamJson, ContentType.APPLICATION_JSON);
        }

        Object bodyObject = request.getBodyObject();
        if (bodyObject != null) {
            String bodyParamJson = JsonUtils.obj2json(bodyObject);
            httpEntity = new StringEntity(bodyParamJson, ContentType.APPLICATION_JSON);
        }

        return httpEntity;
    }

    private static Header[] createHeaders(Map<String, Object> headers) {
        List<Header> headerList = new ArrayList();
        Iterator var2 = headers.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            String value = entry.getValue().toString();
            if (value != null) {
                String key = (String)entry.getKey();
                BasicHeader header = new BasicHeader(key, value);
                headerList.add(header);
            }
        }

        return (Header[])headerList.toArray(new Header[headerList.size()]);
    }

    private static String getResponse(CloseableHttpResponse response) {
        if (response == null) {
            return null;
        } else {
            String result = null;

            try {
                int statusCode = response.getStatusLine().getStatusCode();
                boolean isSuccess = statusCode >= 200 && statusCode < 300;
                if (isSuccess) {
                    HttpEntity entity = response.getEntity();
                    result = EntityUtils.toString(entity);
                }
            } catch (IOException var15) {
                System.err.println(var15);
            } catch (ParseException var16) {
                System.err.println(var16);
            } finally {
                try {
                    response.close();
                } catch (IOException var14) {
                    System.err.println(var14);
                }

            }

            return result;
        }
    }

    private static enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE;

        private HttpMethod() {
        }
    }
}
