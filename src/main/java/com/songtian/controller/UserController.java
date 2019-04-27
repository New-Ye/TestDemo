package com.songtian.controller;

import com.songtian.entity.User;
import com.songtian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    //去默认页面login
    @RequestMapping("/")
    public String index() {
        return "login";
    }


    //登录
    @RequestMapping("login")
    public String login(User user, ModelMap modelMap, HttpSession session) {
        String name = user.getName();
        String pwd = user.getPwd();
        //System.out.println(user.getName());
        User user1;
        //用户名要放session
        if ((user1 = userService.selectByName(name)) != null) {
            System.out.println(user1.getPwd());
            if (pwd.equals(user1.getPwd())) {
                session.setAttribute("name", name + "登陆成功");

                //modelMap.put("msg", name + "登陆成功");
                return "redirect:/selectAllUser";
            } else {
                modelMap.put("msg", "密码错误");
            }
        } else {
            modelMap.put("msg", "用户不存在");
        }

        return "index";
    }


    //去注册页面
    @RequestMapping("toRegister")
    public String toRegister() {
        return "register";
    }

    //注册
    @RequestMapping("register")
    public String register(String name,
                           String pwd,
                           int sex,
                           int age,
                           String birthdate, ModelMap modelMap) {

        if (userService.selectByName(name) != null) {
            modelMap.put("msg", "用户名重复");

            return "register";
        } else {

            User user = new User();
            user.setName(name);
            user.setPwd(pwd);
            user.setSex(sex);
            user.setAge(age);

            user.setBirthdate(birthdate);

            int i = userService.register(user);
            if (i > 0) {
                modelMap.put("msg", "插入成功");
            } else {
                modelMap.put("msg", "插入失败");
            }
        }
        return "index";
    }

    //查询所有人
    @RequestMapping("selectAllUser")
    public String selectAllUser(Model model) {

        List<User> list = userService.selectAllUser();
        model.addAttribute("selectAll", list);
        return "index";
    }

    //根据名字删除用户
    @RequestMapping("deleteByName")
    public String deleteByName(@RequestParam String name, ModelMap modelMap, HttpServletRequest request, HttpSession session) {

        request.setAttribute("name", name);

        int i = userService.deleteByName(name);
        if (i > 0) {
            modelMap.put("msg", "删除成功");
        } else {
            modelMap.put("msg", "删除失败");
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(ModelMap modelMap, @RequestParam String name) {
        modelMap.put("name", name);
        return "update";
    }

    //根据名字修改信息
    @RequestMapping("updateByName")
    public String updateByName(User user, ModelMap modelMap, HttpSession httpSession) {
        //判断是否登录
        if (httpSession.getAttribute(user.getName()) != null && httpSession.getAttribute(user.getName()) != "") {
            //System.out.println(user.getBirthdate());
            int i = userService.updateByName(user);

            if (i > 0) {
                modelMap.put("msg", "更新成功");
            } else {
                modelMap.put("msg", "更新失败");
            }
            return "index";


        } else {
            modelMap.put("msg", "请先登录");
            return "login";
        }

    }

    //注销
    @RequestMapping("Logout")
    public String Logout(HttpSession session) {
        //注销session
        session.invalidate();
        System.out.println();
        System.out.println("注销成功");
        return "login";
    }

    //两表查询 改 三表联查（分步查询）undo
    @RequestMapping("selectFeaturesBySlaveAndUser")
    public String selectFeaturesBySlaveAndUser() {
        List<User> list = userService.selectFeaturesBySlaveAndUser("gay");
        System.out.println();
        System.out.println(list);
        //System.out.println("0k");
        return null;

    }

    @RequestMapping("slelectByBlurry")
    public String slelectByBlurry() {
        List<User> list = userService.slelectByBlurry("三");
        //List<User> list=userService.slelectByBlurry("%三%");
        System.out.println(list);

        return null;
    }

    //文件上传1
    //上传不了其他类型文件，如.apk
/*    @RequestMapping("upFile")
    public String upFile(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request, ModelMap modelMap) {
        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + fileName);
        //新文件名
        String newFileName = UUID.randomUUID() + fileName;
        // 获得项目的路径
        ServletContext servletContext = request.getSession().getServletContext();
        // 上传位置
        String path = servletContext.getRealPath("/file") + "/";// 设定文件保存的目录

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        if (!file.isEmpty()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) {
                    fileOutputStream.write(b);
                }
                fileOutputStream.close();
                in.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        System.out.println("上传文件到:" + path + newFileName);
        // 保存文件地址，用于JSP页面回显
        //modelMap.addAttribute("fileUrl", path + newFileName);
        if (path != null && path != "" && newFileName != null && newFileName != "") {
            modelMap.put("fileUrl", "文件已成功上传到" + path + newFileName);
        }
        return "index";
    }*/

//文件上传2
    @RequestMapping("upFile")
    public String upFile(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
        if (multipartFile!=null&&!multipartFile.isEmpty()){
            //获取原文件名
            String originalFilename=multipartFile.getOriginalFilename();
            //获取原文件名，无后缀
            String fileNamePrefix=originalFilename.substring(0,originalFilename.lastIndexOf("."));
            //处理原文件名
            String newFileNamePerfix = UUID.randomUUID() + fileNamePrefix;
            //得到新文件名
            String newFileName=newFileNamePerfix+originalFilename.substring(originalFilename.lastIndexOf("."));
            // 获得项目的路径
            ServletContext servletContext = request.getSession().getServletContext();
            // 上传位置
            String path = servletContext.getRealPath("/file") + "/";// 设定文件保存的目录
            //构建文件对象
            File file=new File(path+newFileName);
            //上传
            try {
                multipartFile.transferTo(file);
                modelMap.addAttribute("fileUrl", "文件已成功上传到" + file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "index";

    }




    //列出所有的图片
/*
    @RequestMapping("listFile")
    public String listFile(HttpServletRequest request,
                           HttpServletResponse response) {
        // 获取上传文件的目录
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String uploadFilePath = sc.getRealPath("/file") + "/"; // 设定文件保存的目录
        // 存储要下载的文件名

        Map<String, String> fileNameMap = new HashMap<String, String>();

        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        return "index";
    }
*/


    @RequestMapping("downFile")
    public String downFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setCharacterEncoding("UTF-8");
        //文件名
        String fileName = request.getParameter("fileName");
        //设置响应头，控制浏览器下载该文件
       response.addHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"ISO8859-1"));
        //自动识别下载文件的类型
        response.setContentType("multipart/form-data");

        try {

            //获取上传文件的位置
            ServletContext servletContext = request.getSession().getServletContext();
            System.out.println("获取上传文件的位置" + servletContext);

            //上传位置
            String fileSaveRootPath = servletContext.getRealPath("/file");
            System.out.println("上传位置" + fileSaveRootPath);

            //得到要下载文件的位置
            File file = new File(fileSaveRootPath + "\\" + fileName);

            System.out.println("要下载文件的位置" + file);

            //如果文件不存在
            if (!file.exists()) {
                request.setAttribute("downFileMsg", "资源已被删除");
                System.out.println("资源已被删除");
                System.out.println(!file.exists());
                return "index";

            }


            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];

            int length = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((length = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                //System.out.println(buffer[length]);
                out.write(buffer, 0, length);
                out.flush();
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "index";
    }

}


