package com.notoeat.utils.springsecurity;

import com.alibaba.fastjson.JSON;
import com.notoeat.core.model.JsonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by l on 2017/4/22.
 */
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        OutputStreamWriter out = null;
        try {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setMsg("验证失败，请检查用户名和密码");
            out = new OutputStreamWriter(httpServletResponse.getOutputStream(),"UTF-8");
            out.write(JSON.toJSONString(jsonResult));
            out.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (out!=null){
                out.close();
            }
        }
    }
}
