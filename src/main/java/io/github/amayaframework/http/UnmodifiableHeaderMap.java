package io.github.amayaframework.http;

import java.util.*;

class UnmodifiableHeaderMap implements HeaderMap {
    private final HeaderMap body;
    private final Map<String, List<String>> map;

    UnmodifiableHeaderMap(HeaderMap body) {
        this.body = body;
        this.map = Collections.unmodifiableMap(body);
    }

    @Override
    public String getFirst(String key) {
        return body.getFirst(key);
    }

    @Override
    public void add(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return body.size();
    }

    @Override
    public boolean isEmpty() {
        return body.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return body.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return body.containsValue(value);
    }

    @Override
    public List<String> get(Object key) {
        List<String> ret = body.get(key);
        if (ret == null) {
            return null;
        }
        return Collections.unmodifiableList(ret);
    }

    @Override
    public List<String> put(String key, List<String> value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends List<String>> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<List<String>> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, List<String>>> entrySet() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        return body.toString();
    }
}
