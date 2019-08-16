package cs545.waa.project.sellingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs545.waa.project.sellingsystem.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
