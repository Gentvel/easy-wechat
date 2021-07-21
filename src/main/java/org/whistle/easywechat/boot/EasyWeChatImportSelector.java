package org.whistle.easywechat.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * EasyWeChat类选择器
 *
 * @author Gentvel
 * @version 1.0.0
 */
@Slf4j
public class EasyWeChatImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //定义扫描包名称
        String[] basePackage = null;
        //判断有@Import注解类上有没有ComponentScan注解
        if(annotationMetadata.hasAnnotation(ComponentScan.class.getName())){
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ComponentScan.class.getName());
            basePackage= (String[])annotationAttributes.get("basePackages");
        }
        //类路径扫描器
        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(true);
        Set<String> classes = new HashSet<>();
        for (String p : basePackage) {
            componentProvider.findCandidateComponents(p).forEach(beanDefinition -> {classes.add(beanDefinition.getBeanClassName());
            log.debug(beanDefinition.getBeanClassName());
            });
        }

        return classes.toArray(new String[classes.size()]);
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return ImportSelector.super.getExclusionFilter();
    }
}
