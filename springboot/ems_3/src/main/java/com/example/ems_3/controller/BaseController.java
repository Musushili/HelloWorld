package com.example.ems_3.controller;

import com.example.ems_3.service.ex.*;
import com.example.ems_3.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/*控制器类的基类*/
public class BaseController {
    //public static final int OK = 200;
/*

    protected final Integer getIdFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("id").toString());
    }

    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
*/

    /**
     * 设置基类控制类是-->统一处理方法抛出的异常
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(4002);
        } else if (e instanceof OrderpNotFoundException) {
            result.setState(4003);
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4004);
        } else if (e instanceof CartNotFoundException) {
            result.setState(4005);
        } else if (e instanceof InsertException) {
            result.setState(5000);
        } else if (e instanceof UpdateException) {
            result.setState(5001);
        } else if (e instanceof DeleteException) {
            result.setState(5002);
        }
        return result;
    }
}
