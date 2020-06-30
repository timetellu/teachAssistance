package cn.itcast.czjf.utils;

import java.util.HashMap;
import java.util.Map;


public class TestJWT {
    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<String,Object>();
        //模拟添加session中的用户数据
        claims.put("id", "2");
        claims.put("name", "小白");
        claims.put("password", "123456");
        //转成token
        String myToken = JavaWebToken.createJavaWebToken(claims);
        System.out.println("我的token数据：" + myToken);
        //token转化为原数据
        Map<String, Object> myTokenMap = JavaWebToken.parserJavaWebToken("ruegfekgoeroghotrjuihweojkrp");
        System.out.println("token转化为Map：" + myTokenMap);

    }
}
