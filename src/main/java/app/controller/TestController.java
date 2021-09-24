package app.controller;

import app.model.HttpRequestVO;
import app.utils.HttpRequestUtils;
import app.utils.IpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {


    @GetMapping("/getRequestIp")
    public String getRequestIp(HttpServletRequest request){
        return IpUtils.getIpAddress(request);
    }

    /**
     * 模拟 http 请求
     * @param args
     */
    public static void main(String[] args) {
        Map<String,Object> param = new HashMap<>();
        Map<String,Object> header = new HashMap<>();
        HttpRequestVO request = new HttpRequestVO();
        request.createQueryRequestByMap("https://httpbin.org/uuid",param,header);
        String response = HttpRequestUtils.getRequest(request);
        System.out.println(response);
    }
}
