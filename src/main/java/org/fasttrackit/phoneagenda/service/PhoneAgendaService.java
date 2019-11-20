package org.fasttrackit.phoneagenda.service;

import org.fasttrackit.phoneagenda.domain.AgendaItem;
import org.fasttrackit.phoneagenda.persistance.PhoneAgendaRepository;
import org.fasttrackit.phoneagenda.transfer.CreateItemRequest;
import org.fasttrackit.phoneagenda.transfer.DeleteRequest;
import org.fasttrackit.phoneagenda.transfer.FilterRequest;
import org.fasttrackit.phoneagenda.transfer.UpdateItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneAgendaService {
    private PhoneAgendaRepository phoneAgendaRepository = new PhoneAgendaRepository();

    public void createItem(CreateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone-agenda-item: " + request);
        phoneAgendaRepository.createItem(request);
    }

    public void updateItem(long id, UpdateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Updating phone-agenda-item: " + request);
        phoneAgendaRepository.updateItem(id, request.getNumber());
    }

    public List<AgendaItem> deleteItem(DeleteRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Deleting item " + request.getId());
        return phoneAgendaRepository.deleteItem(request.getId());
    }

    public List<AgendaItem> getAgendaItems(FilterRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Retrieving phone-agenda-items...");
        return phoneAgendaRepository.getAgendaItems(request.getFirstName(), request.getLastName());
    }

}
