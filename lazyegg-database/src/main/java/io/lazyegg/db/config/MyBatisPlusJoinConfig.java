package io.lazyegg.db.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import icu.mhb.mybatisplus.plugln.injector.JoinDefaultSqlInjector;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MyBatisPlusJoinConfig extends JoinDefaultSqlInjector {

    /**
     * @param mapperClass 当前mapper
     * @param tableInfo  表信息
     * @return 方法列表
     */
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        return super.getMethodList(mapperClass, tableInfo);
    }
}
