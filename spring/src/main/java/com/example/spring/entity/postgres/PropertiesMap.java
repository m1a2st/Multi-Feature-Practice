package com.example.spring.entity.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties_map")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesMap {

    @Id
    @Column(name = "prop_id")
    private String id;

    @Column(name = "prop_group")
    private Integer propGroup;

    @Column(name = "prop_key")
    private String key;

    @Column(name = "value")
    private String value;
}
