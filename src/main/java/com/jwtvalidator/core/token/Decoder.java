package com.jwtvalidator.core.token;

import java.util.Map;

public interface Decoder {
    Map<String, String> decode(String token);
}
