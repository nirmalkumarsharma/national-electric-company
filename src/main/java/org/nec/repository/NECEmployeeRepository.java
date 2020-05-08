package org.nec.repository;

import java.util.List;
import java.util.UUID;

import org.nec.entity.NECEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NECEmployeeRepository extends JpaRepository<NECEmployee, UUID> {
	List<NECEmployee> findByNecEmailAndNecPassword(String necEmail, String necPassword);
	List<NECEmployee> findByNecEmail(String necEmail);
}
