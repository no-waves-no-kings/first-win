package com.nowavesnokings.firstwin.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author ssx
 * @version V1.0
 * @className JsonDateSecondTimeStampSerializer
 * @description Json字符串转为秒为单位时间戳
 * @date 2020-12-24 15:07
 * @since 1.8
 */
public class Date2SecondLongSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
