package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.cft.template.model.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query("select b from Bill b where b.user.id=:userid")
    List<Bill> getBills(@Param("userid") Long userid);

    @Modifying
    @Query("update Bill b set b.status = 'no' where b.id = :id")
    void changeStatus(@Param("id") Long id);

    @Modifying
    @Query("update Bill b set b.amountRemains = b.amountRemains - :sum where b.id = :id")
    void changeAmountRemains(@Param("id") Long id, @Param("sum") Long sum);
}
