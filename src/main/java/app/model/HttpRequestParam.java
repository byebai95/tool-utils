package app.model;

import java.util.Map;

public class HttpRequestParam {
    private static final int CONNECTION_REQUEST_TIMEOUT = 3000;
    private static final int CONNECTION_TIMEOUT = 3000;
    private static final int SOCKET_TIMEOUT = 30000;
    private String uri;
    private Map<String, Object> queryParams;
    private Object queryObject;
    private Map<String, Object> bodyParams;
    private Object bodyObject;
    private Map<String, Object> headers;
    private final int connectionRequestTimeout;
    private final int connectTimeout;
    private final int socketTimeout;

    public HttpRequestParam() {
        this.connectionRequestTimeout = CONNECTION_REQUEST_TIMEOUT;
        this.connectTimeout = CONNECTION_TIMEOUT;
        this.socketTimeout = SOCKET_TIMEOUT;
    }

    public HttpRequestParam createQueryRequestByMap(String uri, Map<String, Object> queryParams) {
        return this.createQueryRequestByMap(uri, queryParams, null);
    }

    public HttpRequestParam createQueryRequestByMap(String uri, Map<String, Object> queryParams, Map<String, Object> headers) {
        this.uri = uri;
        this.queryParams = queryParams;
        this.headers = headers;
        return this;
    }

    public HttpRequestParam createQueryRequestByObject(String uri, Object queryObject) {
        return this.createQueryRequestByObject(uri, queryObject, null);
    }

    public HttpRequestParam createQueryRequestByObject(String uri, Object queryObject, Map<String, Object> headers) {
        this.uri = uri;
        this.queryObject = queryObject;
        this.headers = headers;
        return this;
    }

    public HttpRequestParam createBodyRequestByMap(String uri, Map<String, Object> bodyParams) {
        return this.createBodyRequestByMap(uri, this.queryParams, null);
    }

    public HttpRequestParam createBodyRequestByMap(String uri, Map<String, Object> bodyParams, Map<String, Object> headers) {
        this.uri = uri;
        this.bodyParams = bodyParams;
        this.headers = headers;
        return this;
    }

    public HttpRequestParam createBodyRequestByObject(String uri, Object bodyObject) {
        return this.createBodyRequestByObject(uri, bodyObject, null);
    }

    public HttpRequestParam createBodyRequestByObject(String uri, Object bodyObject, Map<String, Object> headers) {
        this.uri = uri;
        this.bodyObject = bodyObject;
        this.headers = headers;
        return this;
    }

    private HttpRequestParam(String uri, Map<String, Object> queryParams, Object queryObject, Map<String, Object> bodyParams, Object bodyObject, Map<String, Object> headers, int connectionRequestTimeout, int connectTimeout, int socketTimeout) {
        this.uri = uri;
        this.queryParams = queryParams;
        this.queryObject = queryObject;
        this.bodyParams = bodyParams;
        this.bodyObject = bodyObject;
        this.headers = headers;
        this.connectionRequestTimeout = connectionRequestTimeout;
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    public static HttpRequestParam.Builder custom() {
        return new HttpRequestParam.Builder();
    }

    public String getUri() {
        return this.uri;
    }

    public Map<String, Object> getQueryParams() {
        return this.queryParams;
    }

    public Object getQueryObject() {
        return this.queryObject;
    }

    public Map<String, Object> getBodyParams() {
        return this.bodyParams;
    }

    public Object getBodyObject() {
        return this.bodyObject;
    }

    public Map<String, Object> getHeaders() {
        return this.headers;
    }

    public int getConnectionRequestTimeout() {
        return this.connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public static class Builder {
        private String uri;
        private Map<String, Object> queryParams;
        private Object queryObject;
        private Map<String, Object> bodyParams;
        private Object bodyObject;
        private Map<String, Object> headers;
        private int connectionRequestTimeout;
        private int connectTimeout;
        private int socketTimeout;

        Builder() {
            this.connectionRequestTimeout = 3000;
            this.connectTimeout = 3000;
            this.socketTimeout = 30000;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public void setQueryParams(Map<String, Object> queryParams) {
            this.queryParams = queryParams;
        }

        public void setQueryObject(Object queryObject) {
            this.queryObject = queryObject;
        }

        public void setBodyParams(Map<String, Object> bodyParams) {
            this.bodyParams = bodyParams;
        }

        public void setBodyObject(Object bodyObject) {
            this.bodyObject = bodyObject;
        }

        public void setHeaders(Map<String, Object> headers) {
            this.headers = headers;
        }

        public void setConnectionRequestTimeout(int connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public void setSocketTimeout(int socketTimeout) {
            this.socketTimeout = socketTimeout;
        }

        public HttpRequestParam build() {
            return new HttpRequestParam(this.uri, this.queryParams, this.queryObject, this.bodyParams, this.bodyObject, this.headers, this.connectionRequestTimeout, this.connectTimeout, this.socketTimeout);
        }
    }
}
