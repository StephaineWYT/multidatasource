package wen.multidatasource.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface BaseDAO<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>, Serializable {
}
