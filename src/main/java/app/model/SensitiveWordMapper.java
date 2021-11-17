package app.model;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author: bai
 * @Date: 2021/11/17 16:09
 */
@Component
public interface SensitiveWordMapper {

    Set<String> list();
}
