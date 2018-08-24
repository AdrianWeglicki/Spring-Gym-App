package com.aweglicki.app.repositories;

import com.aweglicki.app.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {

}
