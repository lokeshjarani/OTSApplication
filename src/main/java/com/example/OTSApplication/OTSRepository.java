package com.example.OTSApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTSRepository extends JpaRepository<OTS, Long> {

}
