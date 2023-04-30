package cdu.cyj.order.handler.mabatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //log.info("开始填充");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
//        this.setFieldValByName("create_time", new Date(), metaObject);
//        this.setFieldValByName("update_time", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
//        this.setFieldValByName("update_time", new Date(), metaObject);
    }
}
