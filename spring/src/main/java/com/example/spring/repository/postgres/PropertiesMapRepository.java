package com.example.spring.repository.postgres;

import com.example.spring.entity.postgres.PropertiesMap;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesMapRepository extends JpaRepository<PropertiesMap, String> {

    List<PropertiesMap> findByPropGroup(Integer groupCode);
}
