package com.qywk.service;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.PermissionAO;
import com.qywk.pojo.ao.RoleAO;

/**
 * @author QiLinHu
 * @date 2024/03/01 0:01
 * @description
 */
public interface AuthService {
    ResultBody refresh(String username);

    ResultBody insertPermission(PermissionAO ao);
    
    ResultBody deletePermission(String id);

    ResultBody updatePermission(String id, RoleAO ao);

    ResultBody getAllPermission(PermissionAO ao, Integer pageNum, Integer pageSize);

    ResultBody getByPermission(String id);

    ResultBody insertRole(RoleAO ao);

    ResultBody updateRole(String id, RoleAO ao);

    ResultBody deleteRole(String id);

    ResultBody getAllRole(RoleAO ao, Integer pageNum, Integer pageSize);

    ResultBody getByIdRole(String id);
}
