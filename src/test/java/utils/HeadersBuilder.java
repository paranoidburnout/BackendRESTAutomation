package utils;

import common.TestsContext;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.FilterableRequestSpecification;
import org.apache.http.HttpHeaders;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class HeadersBuilder {
    private final FilterableRequestSpecification specification;

    public HeadersBuilder() {
        specification = (FilterableRequestSpecification) given();
    }

    public HeadersBuilder withAccept() {
        specification.header(new Header(HttpHeaders.ACCEPT, JSON.toString()));
        return this;
    }

    public HeadersBuilder withContentType() {
        specification.header(new Header(HttpHeaders.CONTENT_TYPE, JSON.toString()));
        return this;
    }

    public HeadersBuilder withCookie() {
        specification.header(new Header(COOKIE_HEADER, COOKIE + TestsContext.get().getToken()));
        return this;
    }

    public HeadersBuilder withAuthorisation() {
        specification.header(new Header(AUTHORISATION_HEADER, AUTHORISATION));
        return this;
    }

    public Headers buildHeaders() {
        return specification.getHeaders();
    }

    static Headers setHeaders() {
        HeadersBuilder headersBuilder = new HeadersBuilder();
        headersBuilder.withContentType().withAccept();
        headersBuilder.withCookie().withAuthorisation();

        return headersBuilder.buildHeaders();
    }
    public static final String AUTHORISATION_HEADER = "Authorisation";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String COOKIE = "token=";
    public static final String AUTHORISATION = "Basic";
}
