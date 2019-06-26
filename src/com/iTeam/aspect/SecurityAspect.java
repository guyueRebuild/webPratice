package com.iTeam.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.iTeam.annotion.IgnoreSecurity;
import com.iTeam.exception.TokenException;
import com.iTeam.token.TokenManager;
import com.iTeam.util.MyConstants;
import com.iTeam.util.WebContextUtil;

/**
 * 拦截Controller类的方法，并从请求头中获取token，最后对token有效性进行判断。
* Title:安全检查切面(是否登录检查) 
* Description: 通过验证Token维持登录状态
*/
@Component
@Aspect
public class SecurityAspect {

  
   private static final Logger log = Logger.getLogger(SecurityAspect.class);

   private TokenManager tokenManager;

   @Resource(name = "tokenManager")
   public void setTokenManager(TokenManager tokenManager) {
       this.tokenManager = tokenManager;
   }

   @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
   public Object execute(ProceedingJoinPoint pjp) throws Throwable {
       // 从切点上获取目标方法
       MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
       log.debug("methodSignature : " + methodSignature);
       Method method = methodSignature.getMethod();
       log.debug("Method : " + method.getName() + " : "
               + method.isAnnotationPresent(IgnoreSecurity.class));
       // 若目标方法忽略了安全性检查,则直接调用目标方法
       if (method.isAnnotationPresent(IgnoreSecurity.class)) {
           return pjp.proceed();
       }

       // 从 request header 中获取当前 token
       String token = WebContextUtil.getRequest().getHeader(
               MyConstants.DEFAULT_TOKEN_NAME);
       // 检查 token 有效性
       log.debug("========================================================================:"+token);
       if (!tokenManager.checkToken(token)) {
           String message = String.format("token [%s] is invalid", token);
           log.debug("message : " + message);
           throw new TokenException(message);
       }
       // 调用目标方法
       return pjp.proceed();
   }
}