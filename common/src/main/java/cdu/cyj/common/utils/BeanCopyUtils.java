package cdu.cyj.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    public static <V> V copyBean(Object source, Class<V> clazz) {

        // 创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static <V> List<V> copyBeanList(List<?> list, Class<V> clazz) {
        // Steam流进行映射
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
