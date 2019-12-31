package cn.zy.userprofile.dao.mysql.service.tag;

import cn.zy.userprofile.dao.mysql.mapper.tag.UserprofileTagCalcMetaMapper;
import cn.zy.userprofile.dao.mysql.pojo.tag.UserprofileTagCalcMetaWithBLOBs;
import cn.zy.userprofile.dao.mysql.service.BaseService;

import java.util.List;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 15:20
 */
public class UserprofileTagCalcMetaService extends BaseService {

    private static UserprofileTagCalcMetaMapper mapper = sqlSession.getMapper(UserprofileTagCalcMetaMapper.class);

    /****************** insert ******************/
    public static int insert(UserprofileTagCalcMetaWithBLOBs record) {
        return mapper.insert(record);
    }

    public static int insertSelective(UserprofileTagCalcMetaWithBLOBs record) {
        return mapper.insertSelective(record);
    }

    /****************** delete ******************/

    /****************** update ******************/

    /****************** select ******************/
    public static List<UserprofileTagCalcMetaWithBLOBs> selectAll() {
        return mapper.selectAll();
    }
}
