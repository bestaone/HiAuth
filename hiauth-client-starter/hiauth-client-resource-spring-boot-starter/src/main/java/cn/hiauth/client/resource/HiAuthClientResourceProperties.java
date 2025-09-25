package cn.hiauth.client.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties("hiauth.client.resource")
public class HiAuthClientResourceProperties implements Serializable {

}
