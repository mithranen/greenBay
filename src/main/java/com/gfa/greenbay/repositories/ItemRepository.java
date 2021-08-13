package com.gfa.greenbay.repositories;

import com.gfa.greenbay.entitiesanddtos.Item;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

  @Query("SELECT i FROM  Item i WHERE i.user.id = :userId AND i.status = 'ACTIVE'")
  List<Item> findFirst20ItemByPageNum(Pageable pageable, Long userId);

  //TODO furute resolving 1 pgnum instead of 0, see app.prop. one-indexed-parameters=true
  //extends PagingAndSortingRepository<Item, Long> instead of CRUD
}
