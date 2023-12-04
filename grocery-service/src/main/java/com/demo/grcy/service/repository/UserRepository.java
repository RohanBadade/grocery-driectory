package com.demo.grcy.service.repository;

import com.demo.grcy.service.domain.UserDetails;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

/**
 * User Repository interface class.
 **/
public interface UserRepository extends PanacheRepository<UserDetails> {
}
