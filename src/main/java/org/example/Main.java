package org.example;

import org.example.EntitiesAPIClient;
import org.example.SimpleItemsAPIClient;

public class Main {
    public static void main(String[] args) {
        EntitiesAPIClient.entitiesGET();
        EntitiesAPIClient.entitiesPOST();

        SimpleItemsAPIClient.itemsGET();
        SimpleItemsAPIClient.itemsPOST();
    }
}
