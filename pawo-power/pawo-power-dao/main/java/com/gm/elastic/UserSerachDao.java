package com.gm.elastic;

import com.gm.entity.SysUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSerachDao extends ElasticsearchRepository<SysUser, String> {

}
