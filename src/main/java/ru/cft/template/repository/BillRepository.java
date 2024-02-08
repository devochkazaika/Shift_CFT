package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.template.model.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select b from Bill b where b.user.id=:userid")
    List<Bill> getBills(@Param("userid") Long userid);

}
