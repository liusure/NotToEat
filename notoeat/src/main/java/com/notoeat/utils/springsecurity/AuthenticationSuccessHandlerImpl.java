package com.notoeat.utils.springsecurity;

import com.alibaba.fastjson.JSON;
import com.notoeat.config.model.ConfigModel;
import com.notoeat.config.service.ConfigService;
import com.notoeat.core.model.JsonResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by l on 2017/4/22.
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource
    private ConfigService<ConfigModel,Integer> configService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 认证成功后，获取用户信息并添加到session中
        JsonResult jsonResult = new JsonResult();
        OutputStreamWriter out = null;
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            httpServletRequest.getSession().setAttribute("user", userDetails);
            jsonResult.setSuccess();
            jsonResult.setMsg("/index");
            ConfigModel configModel = configService.getConfigByName("visitorNum");
            Integer visitorNum = null;
            try {
                visitorNum = Integer.parseInt(configModel.getValue());
            } catch (NumberFormatException e) {
                visitorNum=0;
            }
            configModel.setValue((visitorNum+1)+"");
            configService.saveOrUpdate(configModel);
            out = new OutputStreamWriter(httpServletResponse.getOutputStream(),"UTF-8");
            out.write(JSON.toJSONString(jsonResult));
            out.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (out!=null){
                out.close();
            }
        }
    }
}
