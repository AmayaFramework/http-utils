package io.github.amayaframework.http;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Objects;

public final class HttpUtil {
    public static final String CONTENT_CHARSET = "charset=";

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
}
