package org.building.pmservice.service.Enity;

import java.util.List;

public class DjqxEnity {

    private String V_SYSTEM;
    private List<QxEnity> items;

    public String getV_SYSTEM() {
        return V_SYSTEM;
    }

    public void setV_SYSTEM(String v_SYSTEM) {
        V_SYSTEM = v_SYSTEM;
    }

    public List<QxEnity> getItems() {
        return items;
    }

    public void setItems(List<QxEnity> items) {
        this.items = items;
    }
}
