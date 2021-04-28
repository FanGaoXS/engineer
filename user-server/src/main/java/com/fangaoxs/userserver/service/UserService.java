package com.fangaoxs.userserver.service;

import com.fangaoxs.userserver.pojo.User;
import com.fangaoxs.userserver.vo.VoUser;
import com.fangaoxs.userserver.vo.VoUserUpdate;
import com.fangaoxs.userserver.vo.VoUserUpdatePassword;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/02/16:51
 * @Description: user表的业务
 */
@Service
public interface UserService {

    /**
     * 根据用户名和密码来验证用户身份
     * @param username 用户名
     * @param password 密码
     * @return 查询到的对象
     */
    User verify(String username, String password);

    /**
     * 根据用户的uuid查询用户
     * @param uuid  用户唯一标识
     * @return  唯一的用户对象
     */
    User selectUserByUuid(String uuid);

    User selectUserById(Integer id);

    User selectUserByIdAndPassword(VoUserUpdatePassword voUserUpdatePassword);

    Boolean insertUser(VoUser voUser);

    Boolean deleteUser(Integer id);

    Boolean updateUser(VoUser voUser);

    Boolean updateUserPassword(VoUserUpdatePassword voUserUpdatePassword);

    Boolean updateUserInfo(VoUserUpdate voUserUpdate);

    List<VoUser> selectUserListCondition(Integer roleId,
                                         Integer currentPage,
                                         Integer pageSize);

    /**
     * 查询记录数
     * @param user
     * @return              记录数
     */
    Long selectTotalSize(User user);
}
