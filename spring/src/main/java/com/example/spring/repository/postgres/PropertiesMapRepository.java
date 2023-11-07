package com.example.spring.repository.postgres;


import com.example.spring.entity.postgres.PropertiesMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertiesMapRepository extends JpaRepository<PropertiesMap, String> {

    List<PropertiesMap> findByPropGroup(Integer groupCode);
}
