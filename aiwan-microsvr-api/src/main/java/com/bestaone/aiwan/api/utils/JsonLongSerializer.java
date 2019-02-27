package com.bestaone.aiwan.api.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonLongSerializer extends JsonSerializer<Object> {

    private final static Long MAX_VALUE = 1000000000000000L;

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(o==null) {
            jsonGenerator.writeNull();
        }

        if( !(o instanceof Long) ){
            throw new IOException("JsonLongSerializer 只能设置在 Long 类型的属性上");
        }

        Long data = (Long) o;
        //值大于MAX_VALUE的长整型，在前端可能丢失精度，所以需要转换为字符串
        if(data>MAX_VALUE){
            jsonGenerator.writeString(data.toString());
        } else {
            jsonGenerator.writeNumber(data);
        }

    }

}