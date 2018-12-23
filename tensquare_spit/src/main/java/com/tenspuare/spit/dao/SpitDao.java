package com.tenspuare.spit.dao;

import com.tenspuare.spit.pojo.Spit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {
}
