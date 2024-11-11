package com.spa.demo.frontend;

import java.util.List;

public interface Manager {
    public void startBackend();
    public void stopBackend();
    public String getItemName();
    public List<String> getItemType(String type);
}
