package com.nowavesnokings.firstwin.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author ssx
 * @version V1.0
 * @className JsonNullHandlerObjectMapper
 * @description 自定义objectmapper，处理null字符串和数组为''或[]
 * @date 2021-01-18 13:12
 * @since 1.8
 */
public class NullHandlerSerializerModifier extends BeanSerializerModifier {
    private final NullStringHandlerSerializer nullStringHandlerSerializer;

    private final NullCollectionHandlerSerializer nullCollectionHandlerSerializer;

    private final NullObjectHandlerSerializer nullObjectHandlerSerializer;

    private final NullPrimitiveHandlerSerializer nullPrimitiveHandlerSerializer;

    public NullHandlerSerializerModifier() {
        this.nullStringHandlerSerializer = new NullStringHandlerSerializer();
        this.nullCollectionHandlerSerializer = new NullCollectionHandlerSerializer();
        this.nullObjectHandlerSerializer = new NullObjectHandlerSerializer();
        this.nullPrimitiveHandlerSerializer = new NullPrimitiveHandlerSerializer();
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        beanProperties.forEach(beanPropertyWriter -> {
            if (isCollection(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(this.nullCollectionHandlerSerializer);
            } else if (isString(beanPropertyWriter)) {
                beanPropertyWriter.assignNullSerializer(this.nullStringHandlerSerializer);
            } else if (isPrimitive(beanPropertyWriter)) {
beanPropertyWriter.assignNullSerializer(this.nullPrimitiveHandlerSerializer);
            } else if (isObject(beanPropertyWriter)) {
beanPropertyWriter.assignNullSerializer(this.nullObjectHandlerSerializer);
            }
        });
        return beanProperties;
    }

    private boolean isObject(BeanPropertyWriter beanPropertyWriter) {
        Class<?> aClass = beanPropertyWriter.getType().getRawClass();
        return !aClass.equals(String.class) && !aClass.isPrimitive() && aClass.isAssignableFrom(Object.class);
    }

    private boolean isPrimitive(BeanPropertyWriter beanPropertyWriter) {
        Class<?> rawClass = beanPropertyWriter.getType().getRawClass();
        return rawClass.isPrimitive();
    }

    private boolean isString(BeanPropertyWriter beanPropertyWriter) {
        Class<?> rawClass = beanPropertyWriter.getType().getRawClass();
        return rawClass.equals(String.class);
    }

    private boolean isCollection(BeanPropertyWriter beanPropertyWriter) {
        Class<?> rawClass = beanPropertyWriter.getType().getRawClass();
        return rawClass.isArray() || rawClass.equals(List.class) || rawClass.equals(Set.class);
    }

    static class NullStringHandlerSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (o == null) {
                jsonGenerator.writeString(StringUtils.EMPTY);
            }
        }
    }

    static class NullCollectionHandlerSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (o == null) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }
    }

    static class NullObjectHandlerSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (o == null) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
    }

    static class NullPrimitiveHandlerSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (o == null) {
                jsonGenerator.writeNull();
            }
        }
    }

}
