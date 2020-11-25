package com.code.repository.controller;

import com.code.repository.redis.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 验证码 控制类
 */
@RestController
public class VerificationCodeController {

    @RequestMapping("/sendCode")
    public void sendShortLetterCode(HttpServletRequest request,HttpServletResponse response){
        String phone = request.getParameter("phone");
        String code = "";//TODO 发送验证码接口，并返回验证码
        //防止恶意发送验证码
        Object n = RedisUtil.stringGetOperations("malicesend:"+phone,null,"");
        if(n==null)
            n = 5;
        Integer mn = Integer.parseInt(n.toString());
        String message = "";
        if(mn>0){
            RedisUtil.stringSetOperations("malicesend:"+phone,mn-1,240000l,"");
            RedisUtil.stringSetOperations("repeat:num:"+phone,5,120000l,"");//判断验证码错误次数，最多5次，每次验证失败-1
            RedisUtil.stringSetOperations("letter:code:"+phone,code,120000l,"");//设置验证码2分钟超时
        }else{
            message = "由于您存在恶意发送验证码";
        }

        try {
           PrintWriter out = response.getWriter();
           out.write("success");
           out.close();
           out.flush();
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    @RequestMapping("/validCode")
    public void validateVerificationCode(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        String phone = request.getParameter("phone");

        String  message = "";
        try {
            //设置若验证码验证错误次数较多，则设置的停止时间
            Object time = RedisUtil.stringGetOperations("unlimitsendtime:"+phone,null,"");
            Object verify = RedisUtil.stringGetOperations("letter:code:"+phone,null,"");
            Object o = RedisUtil.stringGetOperations("repeat:num:"+phone,null,"");
            if(o==null)//若没有重复次数，说明验证码已超时，默认为1
                o = "1";
            Integer num = Integer.parseInt(o.toString());
            if(time==null&&num>0){//若没有恶意输入并且错误次数没有超过5次，则
                if(verify==null){
                    if(code.equals(verify)){
                        message = "success";
                    }else{
                        if(o!=null){
                            RedisUtil.stringSetOperations("repeat:num:"+phone,num-1,null,"ex");
                        }
                        message = "验证码错误，请重新输入！";
                    }
                }else{
                    message = "验证码已失效，请重新发送验证码。";
                }
            }else{
                if(time==null)
                    time = "5";
                Integer t  = Integer.parseInt(time.toString());
                RedisUtil.stringSetOperations("unlimitsendtime:"+phone,t*2,t*2*1000*60l,"");
                message = "由于你验证码错误次数超过5次！将停止你发送验证码："+t*2 +"分钟！剩余："+RedisUtil.TTL("unlimitsendtime:"+phone)/(1000*60)+" 分钟";
            }

            PrintWriter out = response.getWriter();
            out.write(message);
            out.close();
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
