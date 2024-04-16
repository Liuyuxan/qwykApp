package com.qywk.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qlh
 * @date 2024/03/20 17:58
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "whitelist")
public class WhiteListProperties {
    private List<String> pathList;
}
