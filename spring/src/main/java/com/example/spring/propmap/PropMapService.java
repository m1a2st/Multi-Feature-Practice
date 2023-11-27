package com.example.spring.propmap;

import com.example.spring.entity.postgres.PropertiesMap;
import com.example.spring.propmap.annotation.PropMapBean;
import com.example.spring.propmap.models.PropMapGroup;
import com.example.spring.repository.postgres.PropertiesMapRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropMapService {

    private final PropertiesMapRepository propertiesMapRepository;
    private final ApplicationContext context;
    private final Environment environment;

    public List<PropertiesMap> findAll() {
        return propertiesMapRepository.findAll();
    }

    public Map<String, String> convertPropResultToMap(List<PropertiesMap> props) {
        if (props == null || props.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap<String, String> resultMap = new HashMap<>();
        props.forEach(p -> resultMap.put(p.getKey(), p.getValue()));
        return resultMap;
    }

    public void refreshPropMapBeansFromDb(PropMapGroup group) {
        Map<String, String> dbPropMapData = getMapByGroup(group);
        refreshPropMapBeans(group, dbPropMapData);
    }

    public Map<String, String> getMapByGroup(PropMapGroup group) {
        List<PropertiesMap> mapByGroupCode = propertiesMapRepository.findByPropGroup(group.getGroupCode());
        return convertPropResultToMap(mapByGroupCode);
    }

    public void refreshPropMapBeans(PropMapGroup group, Map<String, String> newData) {
        List<Object> propMapBeans = getPropMapBeansByGroup(group);
        for (Object propMapBean : propMapBeans) {
            // 使用同步鎖，鎖住 prop map bean，避免其他執行續同時讀取造成髒值
            synchronized (propMapBean) {
                PropMapBeanReflector propMapBeanReflector = new PropMapBeanReflector(propMapBean, environment);
                propMapBeanReflector.reflectDataToInstance(newData);
            }
        }
    }

    public List<Object> getPropMapBeansByGroup(PropMapGroup group) {
        String[] beanNamesForAnnotation = context.getBeanNamesForAnnotation(PropMapBean.class);
        // 取得所有符合 group 的 bean
        return Arrays.stream(beanNamesForAnnotation)
                .map(context::getBean)
                .filter((bean) ->
                        bean.getClass().getAnnotation(PropMapBean.class).group().equals(group))
                .collect(Collectors.toList());
    }
}
