package com.quick.start.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.quick.start.domain.User;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, UUID> {

}
