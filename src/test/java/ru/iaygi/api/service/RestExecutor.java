package ru.iaygi.api.service;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.NoAuthScheme;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class RestExecutor {

    private String proxyHost = null;
    private int proxyPort = 0;
    private String basePath = "";
    private String baseURI = "";
    private Object body = null;
    private Response response = null;
    private boolean useProxy = false;
    private boolean resetAuth = false;
    private boolean uriDefined = false;
    private boolean pathDefined = false;
    private boolean bodyDefined = false;
    private boolean resetRequest = true;
    private RestAssuredConfig restAssuredConfig = RestAssured.config();
    private AuthenticationScheme authentication = new NoAuthScheme();
    private RequestSpecBuilder requestBuilder = new RequestSpecBuilder();

    public RestExecutor() {
        appendDefaultCharset(false);
        setRelaxedHTTPSValidation();
    }

    public RestExecutor(String baseURI) {
        baseURI(baseURI);
        appendDefaultCharset(false);
        setRelaxedHTTPSValidation();
    }

    @Step("Ответ сервера содержит {condition}")
    public RestExecutor shouldHave(Condition condition) {
        condition.check(response);
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public <T> T getResponseAs(Class<T> tClass) {
        return response.as(tClass);
    }

    public <T> T getResponseAs(String path, Class<T> tClass) {
        return response.jsonPath().getObject(path, tClass);
    }

    public <T> List<T> getResponseAsList(Class<T> tClass) {
        return getResponseAsJson().getList("$", tClass);
    }

    public JsonPath getResponseAsJson() {
        return response.jsonPath();
    }

    public String getResponseAsString() {
        return response.asString();
    }

    public XmlPath getResponseAsHtml() {
        return response.htmlPath();
    }

    public XmlPath getResponseAsXml() {
        return response.xmlPath();
    }

    public String getResponseMessage() {
        return response.getStatusLine();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public Headers getHeaders() {
        return response.getHeaders();
    }

    public String getHeaderValue(String headerName) {
        return response.getHeader(headerName);
    }

    public String getContentType() {
        return response.contentType();
    }

    public int getResponseSize() {
        return getResponseAsJson().getList("$").size();
    }

    public String getBodyByPath(String path) {
        return response.path(path).toString();
    }

    /**
     * Получить из ответа сервера все куки.
     */
    public Map<String, String> getAllCookies() {
        return response.getCookies();
    }

    /**
     * Получить из ответа сервера куки по её названию.
     */
    public String getCookieValue(String cookieName) {
        return response.getCookie(cookieName);
    }

    public Cookies getDetailedCookies() {
        return response.getDetailedCookies();
    }

    public RestExecutor setResetAuth(boolean value) {
        resetAuth = value;

        return this;
    }

    public RestExecutor setResetRequest(boolean value) {
        resetRequest = value;

        return this;
    }

    public static void resetRequestSpecification() {
        RestAssured.requestSpecification = null;
    }

    public RestExecutor resetAuth() {
        authentication = new NoAuthScheme();

        return this;
    }

    public RestExecutor resetRequest() {
        resetRequestSpecification();
        requestBuilder = new RequestSpecBuilder();

        return this;
    }

    public RestExecutor param(String paramKey, Object paramValue) {
        requestBuilder.addParam(paramKey, paramValue);

        return this;
    }

    public RestExecutor formParam(String paramKey, String paramValue) {
        requestBuilder.addFormParam(paramKey, paramValue);
        return this;
    }

    public RestExecutor pathParam(String paramKey, Object paramValue) {
        requestBuilder.addPathParam(paramKey, paramValue);

        return this;
    }

    public RestExecutor queryParam(String paramKey, Object paramValue) {
        requestBuilder.addQueryParam(paramKey, paramValue);

        return this;
    }

    public RestExecutor header(String headerKey, String headerValue) {
        requestBuilder.addHeader(headerKey, headerValue);

        return this;
    }

    public RestExecutor cookie(String cookieName, String cookieValue) {
        requestBuilder.addCookie(cookieName, cookieValue);

        return this;
    }

    public RestExecutor cookies(Map<String, String> cookies) {
        for (Map.Entry<String, String> cookie : cookies.entrySet()) {
            cookie(cookie.getKey(), cookie.getValue());
        }

        return this;
    }

    public RestExecutor cookies(Cookies cookies) {
        requestBuilder.addCookies(cookies);

        return this;
    }

    public RestExecutor followRedirects(boolean value) {
        restAssuredConfig = restAssuredConfig.redirect(RedirectConfig.redirectConfig().followRedirects(value));

        return this;
    }

    public RestExecutor appendDefaultCharset(boolean value) {
        restAssuredConfig = restAssuredConfig.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(value));

        return this;
    }

    public RestExecutor setRelaxedHTTPSValidation() {
        RestAssured.useRelaxedHTTPSValidation();

        return this;
    }

    public String getURI() {
        String resutlUri = "";
        resutlUri += uriDefined ? baseURI : RestAssured.baseURI;
        resutlUri += pathDefined ? basePath : RestAssured.basePath;

        return resutlUri;
    }

    public RequestSpecBuilder getRequestBuilder() {
        return requestBuilder;
    }

    public RestExecutor baseURI(String baseURI) {
        this.baseURI = baseURI;
        uriDefined = true;

        return this;
    }

    public RestExecutor body(Object body) {
        if (body == null) {
            return this;
        }
        this.body = body;
        bodyDefined = true;

        return this;
    }

    public RestExecutor basePath(String basePath) {
        this.basePath = basePath;
        pathDefined = true;

        return this;
    }

    public RestExecutor auth(AuthenticationScheme authentication) {
        this.authentication = authentication;

        return this;
    }

    public RestExecutor contentType(ContentType contentType) {
        requestBuilder.setContentType(contentType);

        return this;
    }

    public RestExecutor setProxy(int port) {
        proxyPort = port;

        return this;
    }

    public RestExecutor setProxy(String host, int port) {
        RestAssured.proxy(host, port);

        return this;
    }

    public RestExecutor setProxy(ProxySpecification proxySpecification) {
        RestAssured.proxy(proxySpecification);

        return this;
    }

    public RestExecutor setSendFile(String fileName, String fileType, String text) {
        return setSendFile("file", fileName, fileType, text);
    }

    public RestExecutor setSendFile(String name, String fileName, String fileType, String text) {
        MultiPartSpecBuilder multipart = new MultiPartSpecBuilder(text)
                .header("Content-Disposition", "form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"")
                .header("Content-Type", fileType);
        requestBuilder.addMultiPart(multipart.build());

        return this;
    }

    private static void setStaticRequestSpec(RequestSpecification specification) {
        RestAssured.requestSpecification = specification;
    }

    private void setProxy() {
        if (useProxy && proxyHost != null) {
            RestAssured.proxy(proxyHost, proxyPort);
        } else if (useProxy) {
            RestAssured.proxy(proxyPort);
        }
    }

    public Response get() {
        return get("");
    }

    public Response get(String uri) {
        log.debug("Send GET request to URI: {uri}");
        return sendRequest(Method.GET, uri);
    }

    public Response post() {
        return post("");
    }

    public Response post(String uri) {
        log.debug("Send POST request to URI: {uri}");
        return sendRequest(Method.POST, uri);
    }

    public Response put() {
        return put("");
    }

    public Response put(String uri) {
        log.debug("Send PUT request to URI: {uri}");
        return sendRequest(Method.PUT, uri);
    }

    public Response delete() {
        return delete("");
    }

    public Response delete(String uri) {
        log.debug("Send DELETE request to URI: {uri}");
        return sendRequest(Method.DELETE, uri);
    }

    public Response patch() {
        return patch("");
    }

    public Response patch(String uri) {
        log.debug("Send PATCH request to URI: {uri}");
        return sendRequest(Method.PATCH, uri);
    }

    private Response sendRequest(Method requestType, String uri) {
        if (uriDefined) {
            requestBuilder.setBaseUri(baseURI);
        }

        if (pathDefined) {
            requestBuilder.setBasePath(basePath);
        }

        if (bodyDefined) {
            requestBuilder.setBody(body);
        }

        requestBuilder.setConfig(restAssuredConfig)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter());

        requestBuilder.setAuth(authentication);
        setStaticRequestSpec(requestBuilder.build());

        if (requestType == Method.GET) {
            response = RestAssured.get(uri);
        } else if (requestType == Method.POST) {
            response = RestAssured.post(uri);
        } else if (requestType == Method.PUT) {
            response = RestAssured.put(uri);
        } else if (requestType == Method.DELETE) {
            response = RestAssured.delete(uri);
        } else if (requestType == Method.PATCH) {
            response = RestAssured.patch(uri);
        }

        if (resetRequest) {
            resetRequest();
        }

        if (resetAuth) {
            resetAuth();
        }

        return response;
    }
}
