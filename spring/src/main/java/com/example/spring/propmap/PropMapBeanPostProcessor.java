package com.example.spring.propmap;

import static java.util.stream.Collectors.toList;

import com.example.spring.entity.postgres.PropertiesMap;
import com.example.spring.propmap.annotation.PropMapBean;
import com.example.spring.propmap.models.PropMapGroup;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PropMapBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware, EnvironmentAware {

    private List<PropertiesMap> propMapList;
    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private PropMapService getPropMapService() {
        return applicationContext.getBean(PropMapService.class);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> propMapClass = bean.getClass();

        if (!propMapClass.isAnnotationPresent(PropMapBean.class)) {
            return bean;
        }

        log.info("process '{}' prop map bean ...", beanName);
        PropMapService propMapService = getPropMapService();

        // initial propMapList data
        if (propMapList == null) {
            propMapList = propMapService.findAll();
        }

        PropMapBean propMapBeanAnnotation = propMapClass.getAnnotation(PropMapBean.class);
        List<PropertiesMap> propsByGroupList = filterPropsByGroup(propMapList, propMapBeanAnnotation.group());
        Map<String, String> propMapGroupMap = propMapService.convertPropResultToMap(propsByGroupList);
        PropMapBeanReflector beanReflector = new PropMapBeanReflector(bean, environment);
        return beanReflector.reflectDataToInstance(propMapGroupMap);
    }

    private List<PropertiesMap> filterPropsByGroup(List<PropertiesMap> srcMap, PropMapGroup group) {
        return srcMap.stream()
                .filter(p -> group.getGroupCode().equals(p.getPropGroup()))
                .collect(toList());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
