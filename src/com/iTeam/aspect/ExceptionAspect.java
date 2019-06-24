package com.iTeam.aspect;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.iTeam.exception.TokenException;
import com.iTeam.exception.UserNotFoundException;
import com.iTeam.response.MyError;
import com.iTeam.response.MyResponse;

@ControllerAdvice   // 控制器增强
@ResponseBody
public class ExceptionAspect {

   /** Log4j日志处理*/
   private static final Logger log = Logger.getLogger(ExceptionAspect.class);

   /**
    * 400 - Bad Request
    */
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler(HttpMessageNotReadableException.class)
   public MyResponse handleHttpMessageNotReadableException(
           HttpMessageNotReadableException e) {
       log.error("could_not_read_json...", e);
       return new MyResponse().failure("could_not_read_json");
   }

   /**
    * 400 - Bad Request
    */
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   @ExceptionHandler({MethodArgumentNotValidException.class})
   public MyResponse handleValidationException(MethodArgumentNotValidException e) {
       log.error("parameter_validation_exception...", e);
       return new MyResponse().failure("parameter_validation_exception");
   }

   /**
    * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
    * 是ServletException的子类,需要Servlet API支持
    */
   @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   public MyResponse handleHttpRequestMethodNotSupportedException(
           HttpRequestMethodNotSupportedException e) {
       log.error("request_method_not_supported...", e);
       return new MyResponse().failure("request_method_not_supported");
   }

   /**
    * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
    * 是ServletException的子类,需要Servlet API支持
    */
   @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
   @ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
   public MyResponse handleHttpMediaTypeNotSupportedException(Exception e) {
       log.error("content_type_not_supported...", e);
       return new MyResponse().failure("content_type_not_supported");
   }

   /**
    * 500 - Internal Server Error
    */
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(TokenException.class)
   public MyResponse handleTokenException(Exception e) {
       log.error("Token is invaild...", e);
       return new MyResponse().failure("Token is invaild");
   }

   /**
    * 500 - Internal Server Error
    */
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(Exception.class)
   public MyResponse handleException(Exception e) {
       log.error("Internal Server Error...", e);
       return new MyResponse().failure("Internal Server Error");
   }
   
   /**
	 * 处理用户查找不到异常
	 * @ExceptionHandler 表明如果控制器的任意处理方法中抛出UserNotFoundException异常，就会调用userNotFound方法来处理异常
	 * @param e
	 * @return 错误信息
	 */
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public MyError userNotFound(UserNotFoundException e){
		log.error("user Not found...");;
		String username=e.getuserName();
		return new MyError(4,"用户："+username+" 找不到");
	}
	
}