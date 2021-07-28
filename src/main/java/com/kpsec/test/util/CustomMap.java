package com.kpsec.test.util;

import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

public class CustomMap extends HashMap<String, Object> {

    //DB에서 받아온 컬럼명을 카멜 형식으로 변경
    @Override
    public Object put(String key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName(key), value);
    }

}
