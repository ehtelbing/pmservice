package org.building.pmservice.service.Enity;

import java.util.List;

public class WorkOrderRetEnity {
    private String SYSNAME;

    private List<WorkOrderRetOEnity> items;

    public String getSYSNAME() {
        return SYSNAME;
    }

    public void setSYSNAME(String SYSNAME) {
        this.SYSNAME = SYSNAME;
    }

    public List<WorkOrderRetOEnity> getItems() {
        return items;
    }

    public void setItems(List<WorkOrderRetOEnity> items) {
        this.items = items;
    }
}
