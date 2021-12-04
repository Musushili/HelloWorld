package com.example.ems_3.controller;

import com.example.ems_3.entity.User;
import com.example.ems_3.service.UserService;
import com.example.ems_3.service.ex.*;
import com.example.ems_3.util.JWTUtils;
import com.example.ems_3.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")//-->用户登录
    public JsonResult<String> login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("用户名或密码为空");
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户名或密码错误");
        }

        String realPassword = user.getPassword();
        //通过在t_user查询到用户密码并与接收的密码进行对比
        System.out.println(realPassword);
        System.out.println(password);
        System.out.println(password.equals(realPassword));

        System.out.println("realpassword="+realPassword);
        System.out.println("password="+password);

        if (realPassword.equals(password)) {
            User u = new User();
            u.setPassword(password);
            u.setUsername(username);
            u.setId(user.getId());
            String data = JWTUtils.getToken(u);//通过用户信息生成token
            return new JsonResult<String>(200, data);
        }
        String data = "登录失败！账号或者密码不对！";
        return new JsonResult<String>(4002, data);
    }


    @PostMapping("/register")//-->注册用户
    public JsonResult<Void> register(String username, String password) {
        System.out.println("username="+username);
        System.out.println("password="+password);

        if (username.isEmpty() || password.isEmpty()) {
            throw new IsEmptyException("用户名或密码为空");
        }
        userService.reg(username, password);
        return new JsonResult<Void>(200);
    }


    @GetMapping("/findById")//-->展示个人信息
    public JsonResult<User> findByUid(@RequestHeader("token") String token) {
        Integer id = JWTUtils.getUserInfo(token);
        User data = userService.findById(id);
        return new JsonResult<User>(200, data);
    }

    /*
        @PostMapping("/changeinfo")
        public JsonResult<Void> changeInfo(User user, HttpSession session) {
            Integer id = getIdFromSession(session);
            String username = getUsernameFromSession(session);
            userService.changeInfo(id, username, user);
            return new JsonResult<Void>(200);
        }
    */
    @RequestMapping("/changeinfo")//-->修改除了密码照片的个人信息
    public JsonResult<User> changeInfo(String username, String sex, Integer age, String num, @RequestHeader("token") String token) {
        System.out.println("username="+username);
        System.out.println("sex="+sex);
        System.out.println("age="+age);
        System.out.println("num="+num);

        Integer id = JWTUtils.getUserInfo(token);

        User user = userService.findById(id);

        if(!username.equals(user.getUsername()) && userService.findByUsername(username)!=null){
            throw new UsernameDuplicateException("用户名已被使用");
        }
        user.setUsername(username);
        user.setSex(sex);
        user.setAge(age);
        user.setNum(num);

        userService.changeInfo(user);
        return new JsonResult<User>(200, user);
    }

    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 10 * 1024 *1024;

    //设置上传文件类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();

    //使用静态块给图片类型赋值
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("/changeAvatar")//-->修改头像信息（）
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, @RequestHeader("token")String token){
        System.out.println("接收的file="+file);

        //这里面抛出的异常是我们自定义的异常，根据项目需要修改
        if (file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE){
            throw new FileSizeException("文件大小超出限制");
        }
        if (!AVATAR_TYPE.contains(file.getContentType())){
            throw new FileTypeException("文件类型异常");
        }

        Integer uid=JWTUtils.getUserInfo(token);
        //获取session中的上传路径
        /*String parent = session.getServletContext()
                .getRealPath("upload");*/


        String parent="/home/feidian/shaowen"+userService.findUimgByUid(uid);


        File dir = new File(parent);
        if (!dir.exists()){//判断这个路径上面的文件是否存在，不存在就创建
            dir.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        //使用uuid重新生成文件名
        String fileName = UUID.randomUUID().toString().toUpperCase() + suffix;

        //将file文件中的数据写入到dest中
        File dest = new File(dir,fileName);
        try {
            file.transferTo(dest);
        } catch (FileStateException e){
            throw new FileSizeException("文件状态异常");
        } catch (IOException e) {
            /*throw new FileUploadIOException();*/
            System.out.println("文件读写异常");
        }
        //文件最后要上传的路径
        String avatar = "http://47.103.198.84:3306/home/feidian/shaowen/upload/" + fileName;
        userService.changeAvatar(uid,avatar);
        return new JsonResult<>(200,avatar);
    }

}
