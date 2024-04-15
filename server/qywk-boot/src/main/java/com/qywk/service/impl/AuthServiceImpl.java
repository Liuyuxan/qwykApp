package com.qywk.service.impl;

import com.qywk.common.entity.ResultBody;
import com.qywk.pojo.ao.PermissionAO;
import com.qywk.pojo.ao.RoleAO;
import com.qywk.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author QiLinHu
 * @date 2024/03/01 0:02
 * @description
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public ResultBody refresh(String username) {
        return null;
    }

    @Override
    public ResultBody insertPermission(PermissionAO ao) {
        return null;
    }

    @Override
    public ResultBody deletePermission(String id) {
        return null;
    }

    @Override
    public ResultBody updatePermission(String id, RoleAO ao) {
        return null;
    }

    @Override
    public ResultBody getAllPermission(PermissionAO ao, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ResultBody getByPermission(String id) {
        return null;
    }

    @Override
    public ResultBody insertRole(RoleAO ao) {
        return null;
    }

    @Override
    public ResultBody updateRole(String id, RoleAO ao) {
        return null;
    }

    @Override
    public ResultBody deleteRole(String id) {
        return null;
    }

    @Override
    public ResultBody getAllRole(RoleAO ao, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ResultBody getByIdRole(String id) {
        return null;
    }
}
