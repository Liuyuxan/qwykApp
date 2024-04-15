package com.qywk.controller.user;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.PermissionAO;
import com.qywk.pojo.ao.RoleAO;
import com.qywk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author QiLinHu
 * @date 2024/02/29 22:01
 * @description 权限模块接口
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * 刷新用户的权限
     * @param username 用户名
     * @return
     */
    @PostMapping("/refresh")
    public ResultBody refresh(@RequestParam(value = "username") String username){
        return authService.refresh(username);
    }

    /**
     * 添加权限
     * @param username
     * @param ao
     * @return
     */
    @PostMapping("/permission/save")
    public ResultBody insertPermission(@RequestHeader("username") String username,
                                       @RequestBody PermissionAO ao){
        return authService.insertPermission(ao);
    }

    /**
     * 更新权限
     * @param username
     * @param id
     * @param ao
     * @return
     */
    @PostMapping("/permission/update")
    public ResultBody updatePermission(@RequestHeader("username") String username,
                                       @RequestParam(value = "id") String id,
                                       @RequestBody RoleAO ao){
        return authService.updatePermission(id, ao);
    }

    /**
     * 删除权限
     * @param username
     * @param id
     * @return
     */
    @PostMapping("/permission/delete")
    public ResultBody deletePermission(@RequestHeader("username") String username,
                                       @RequestParam(value = "id") String id){
        return authService.deletePermission(id);
    }

    /**
     * 查看权限
     * @param username
     * @param ao
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/permission/getAll")
    public ResultBody getAllPermission(@RequestHeader("username") String username,
                                       @RequestBody PermissionAO ao,
                                       @RequestParam(value = "page") Integer pageNum,
                                       @RequestParam(value = "size") Integer pageSize){
        return authService.getAllPermission(ao, pageNum, pageSize);
    }

    /**
     *
     * @param username
     * @param id
     * @return
     */
    @PostMapping("/permission/getById")
    public ResultBody getByIdPermission(@RequestHeader("username") String username,
                                  @RequestParam(value = "id") String id){
        return authService.getByPermission(id);
    }

    /**
     * 添加角色
     * @param username
     * @param ao
     * @return
     */
    @PostMapping("/role/save")
    public ResultBody insertRole(@RequestHeader("username") String username,
                                 @RequestBody RoleAO ao){
        return authService.insertRole(ao);
    }

    /**
     * 更新角色信息
     * @param username
     * @param id
     * @param ao
     * @return
     */
    @PostMapping("/role/update")
    public ResultBody updateRole(@RequestHeader("username") String username,
                                 @RequestParam(value = "id") String id,
                                 @RequestBody RoleAO ao){
        return authService.updateRole(id, ao);
    }

    /**
     *
     * @param username
     * @param id
     * @return
     */
    @PostMapping("/role/delete")
    public ResultBody deleteRole(@RequestHeader("username") String username,
                                 @RequestParam(value = "id") String id){
        return authService.deleteRole(id);
    }

    /**
     *
     * @param username
     * @param ao
     * @return
     */
    @PostMapping("/role/getAll")
    public ResultBody getAllRole(@RequestHeader("username") String username,
                                 @RequestBody RoleAO ao,
                                 @RequestParam(value = "page") Integer pageNum,
                                 @RequestParam(value = "size") Integer pageSize){
        return authService.getAllRole(ao, pageNum, pageSize);
    }

    /**
     *
     * @param username
     * @param id
     * @return
     */
    @PostMapping("/role/getById")
    public ResultBody getByIdRole(@RequestHeader("username") String username,
                                 @RequestParam String id){
        return authService.getByIdRole(id);
    }


}
