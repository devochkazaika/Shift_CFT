package ru.cft.template.сontroller.Wallet.WalletTypes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMaintenance {
    private Long id;
    private Long maintenanceNumber;
    private Boolean status;
}
