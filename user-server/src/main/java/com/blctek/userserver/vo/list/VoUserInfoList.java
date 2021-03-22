package com.blctek.userserver.vo.list;

import com.blctek.userserver.pojo.User;
import com.blctek.userserver.vo.VoUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/03/22/18:37
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoUserInfoList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<VoUserInfo> items;

    public VoUserInfoList(List<User> userList){
        this.items = new ArrayList<>();
        userList.forEach(user -> this.items.add(new VoUserInfo(user)));
    }

}
