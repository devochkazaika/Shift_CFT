package ru.cft.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.cft.template.model.Wallet;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("select w from Wallet w where w.user.id = :id")
    Optional<Wallet> findById(@Param("id") Long id);

    @Modifying
    @Query("update Wallet w set w.amount = w.amount + :amount where w.user.id = :id")
    void hesoyam(@Param("id") Long id,
                 @Param("amount") Long amount);
}
