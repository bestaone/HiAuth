package cn.hiauth.mgr.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonLongSerializer extends JsonSerializer<Long> {

    private final static Long MAX_VALUE = 1000000000000000L;

    @Override
    public void serialize(Long o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if(o==null) {
            jsonGenerator.writeNull();
        }

        //值大于MAX_VALUE的长整型，在前端可能丢失精度，所以需要转换为字符串
        if(o>MAX_VALUE){
            jsonGenerator.writeString(o.toString());
        } else {
            jsonGenerator.writeNumber(o);
        }

    }

}