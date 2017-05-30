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
    private static final String TYPE_AJAX = "XMLHttpRequest";
    @Resource
    private ConfigService<ConfigModel,Integer> configService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 认证成功后，获取用户信息并添加到session中
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            httpServletRequest.getSession().setAttribute("user", userDetails);
            ConfigModel configModel = configService.getConfigByName("visitorNum");
            Integer visitorNum = null;
            try {
                visitorNum = Integer.parseInt(configModel.getValue());
            } catch (NumberFormatException e) {
                visitorNum=0;
            }
            configModel.setValue((visitorNum+1)+"");
            configService.saveOrUpdate(configModel);
            String requestType = httpServletRequest.getHeader("X-Requested-With");
            if(TYPE_AJAX.equals(requestType)){
                try ( OutputStreamWriter out = new OutputStreamWriter(httpServletResponse.getOutputStream(),"UTF-8")){
                    JsonResult jsonResult = new JsonResult();
                    jsonResult.setSuccess();
                    jsonResult.setResult("/index");
                    jsonResult.setMsg("登陆成功");
                    out.write(JSON.toJSONString(jsonResult));
                    out.flush();
                }
            }else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/index");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
