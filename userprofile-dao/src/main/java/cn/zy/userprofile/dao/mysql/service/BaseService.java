package cn.zy.userprofile.dao.mysql.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * author: hufenggang
 * email: hufenggang2019@gmail.com
 * date: 2019/12/31 15:17
 */
public abstract class BaseService {
    public static Logger logger = LoggerFactory.getLogger(BaseService.class);

    protected static SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    protected static SqlSessionFactory sessionFactory;
    protected static SqlSession sqlSession;

    static {
        try {
            sessionFactory = sqlSessionFactoryBuilder.build(
                    Resources.getResourceAsReader("mybatis-config.xml"),
                    "development");
            sqlSession = sessionFactory.openSession(true);
        } catch (IOException e) {
            logger.error("open sql session error, {}", e);
        }
    }


    public static void closeSession() {
        try {
            sqlSession.close();
        } catch (Exception e) {
            logger.error("close sql session error, {}", e);
        } finally {
            sqlSession = null;
        }
    }


    public static void openSession() {
        if (sqlSession == null) {
            try {
                sessionFactory = sqlSessionFactoryBuilder.build(
                        Resources.getResourceAsReader("mybatis-config.xml"),
                        "development");
                sqlSession = sessionFactory.openSession(true);
            } catch (IOException e) {
                logger.error("open sql session error, {}", e);
            }
        }
    }
}
