package org.fasttrackit.phoneagenda;


import org.fasttrackit.phoneagenda.domain.AgendaItem;
import org.fasttrackit.phoneagenda.persistance.PhoneAgendaRepository;
import org.fasttrackit.phoneagenda.transfer.CreateItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CreateItemRequest request = new CreateItemRequest();
        request.setNumber("0788877655");
        request.setFirstName("Mircea");
        request.setLastName("Dan");

        PhoneAgendaRepository phoneAgendaRepository = new PhoneAgendaRepository();
        // phoneAgendaRepository.createItem(request);
        //  phoneAgendaRepository.updateItem(3, "0722222222");
        //  phoneAgendaRepository.deleteItem(8);
        List<AgendaItem> agendaItems = phoneAgendaRepository.getAgendaItems();
        System.out.println(agendaItems);
    }
}
