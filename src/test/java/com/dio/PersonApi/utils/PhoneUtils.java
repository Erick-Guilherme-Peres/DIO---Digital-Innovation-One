package com.dio.PersonApi.utils;

import com.dio.PersonApi.Entity.Phone;
import com.dio.PersonApi.dto.request.PhoneDTO;
import com.dio.PersonApi.enums.PhoneType;

public class PhoneUtils {

    private static final long PHONE_ID = 1L;
    private static final String PHONE_NUMBER = "1199999-9999";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
