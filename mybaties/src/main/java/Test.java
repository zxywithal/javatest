import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.example.domain.TUser;
import org.mybatis.example.enums.Sex;
import org.mybatis.example.mapper.TUserMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zhangxiaoyun3 on 2018/11/1.
 */
@Slf4j
public class Test {
    public static void main(String[] args) {


//        Configuration configuration = new Configuration();
//        configuration.setCrypKeys("TUser:pwd,userName|TUserRole:userId|");
//        configuration.setDecrypKeys("TUser:pwd,userName|TUserRole:userId|");
//        configuration.setAeskeys("0123456789abcdef");
//        configuration.setExampleSuffix("Example");
//        configuration.setExamplePrefix("org.mybatis.example.example");
//        PropertiesUtils.init();

        SqlSession sqlSession = null;
        try {
            LogFactory.useLog4J2Logging();
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //下面这句话构建的是一个DefaultSqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            org.apache.ibatis.session.Configuration configuration1 = sqlSessionFactory.getConfiguration();
            System.out.println("========="+configuration1);

            //创建session的时候会创建当前session的一级缓存
            sqlSession = sqlSessionFactory.openSession(ExecutorType.REUSE,Boolean.TRUE);

//            System.out.println(sqlSession);

            //user 处理
            TUserMapper tUserMapper = sqlSession.getMapper(TUserMapper.class);

            TUser tUser1 = new TUser();
            tUser1.setId(123);
            tUser1.setUserName("99999");
            tUser1.setSex("F");

//            tUserMapper.updateUser(tUser1);
            //insert 语句如果有加密存储的字段 不会影响加密字段的值
            ArrayList<TUser> tUsers = new ArrayList<>();

            for (int i = 200; i < 300; i++) {
                TUser tUser = new TUser();
                tUser.setUserName("tzxy"+i);
                tUser.setSex(Sex.MAIL.code);
                tUser.setPwd("zxy9999999");
                tUser.setNote("啦啦啦啦啦");
                tUser.setAvailable(0);
                tUser.setDeleteStatus(false);
                tUser.setUserName("insertBatch"+i);
                tUsers.add(tUser);
            }

//            tUserMapper.inserBatch(tUsers);
            TUser param = new TUser();
            param.setId(216);
            Map<String, String> describe = BeanUtils.describe(param);

//            TUser tUsers1 = tUserMapper.selectById(216);
            PageBounds pageBounds = new PageBounds(1, 20, Boolean.TRUE);

            PageList<TUser> pageLis = tUserMapper.selectByName("%tzxy%",pageBounds);
            Paginator paginator = pageLis.getPaginator();

            pageLis.stream()
                    .skip(0)
                    .filter(obj -> obj.getUserName().length() > 0)
                    .forEach(System.out::println);
            System.out.println("hhhhhh");
            System.out.println("hhhhhh");

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
//        sqlSession.selectOne()
    }
}
