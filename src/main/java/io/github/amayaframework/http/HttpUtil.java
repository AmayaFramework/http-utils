package io.github.amayaframework.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple set of http utilities.
 */
public final class HttpUtil {
    public static final String CONTENT_CHARSET = "charset=";
    public static final String CONTENT_HEADER = "Content-Type";
    public static final String COOKIE_HEADER = "Cookie";
    public static final String SET_COOKIE_HEADER = "Set-Cookie";
    private static final Pattern QUERY_VALIDATOR = Pattern.compile("^(?:[^&]+=[^&]+(?:&|$))+$");
    private static final Pattern QUERY = Pattern.compile("([^&]+)=([^&]+)");

    public static String generateContentHeader(ContentType type, Charset charset) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(charset);
        String ret = type.getHeader();
        if (type.isString()) {
            ret += "; " + CONTENT_CHARSET + charset.name().toLowerCase(Locale.ROOT);
        }
        return ret;
    }

    public static HeaderMap unmodifiableHeaderMap(HeaderMap headerMap) {
        return new UnmodifiableHeaderMap(headerMap);
    }

    public static Map<String, List<String>> parseQueryString(String source, Charset charset)
            throws UnsupportedEncodingException {
        Map<String, List<String>> ret = new HashMap<>();
        if (source == null || source.isEmpty()) {
            return ret;
        }
        if (!QUERY_VALIDATOR.matcher(source).matches()) {
            return ret;
        }
        Matcher matcher = QUERY.matcher(source);
        String charsetName = charset.name();
        while (matcher.find()) {
            String value = URLDecoder.decode(matcher.group(2), charsetName);
            ret.computeIfAbsent(matcher.group(1), key -> new ArrayList<>()).add(value);
        }
        return ret;
    }

    public static Charset parseCharsetHeader(String header, Charset defaultCharset) {
        if (header == null) {
            return defaultCharset;
        }
        header = header.trim();
        if (!header.startsWith("charset")) {
            return defaultCharset;
        }
        int position = header.indexOf('=');
        if (position < 0) {
            return defaultCharset;
        }
        try {
            return Charset.forName(header.substring(position + 1));
        } catch (Exception e) {
            return defaultCharset;
        }
    }
}
