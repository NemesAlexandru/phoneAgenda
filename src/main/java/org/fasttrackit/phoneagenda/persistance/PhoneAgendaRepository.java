package org.fasttrackit.phoneagenda.persistance;

import org.fasttrackit.phoneagenda.domain.AgendaItem;
import org.fasttrackit.phoneagenda.transfer.CreateItemRequest;
import org.fasttrackit.phoneagenda.transfer.FilterRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneAgendaRepository {

    public void createItem(CreateItemRequest request) throws SQLException, IOException, ClassNotFoundException {
        String sql = "INSERT INTO agenda_item (phone_number, first_name, last_name) VALUES (?, ?, ?)";

        //try-with-resources
        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, request.getNumber());
            preparedStatement.setString(2, request.getFirstName());
            preparedStatement.setString(3, request.getLastName());

            preparedStatement.executeUpdate();
        }
    }

    public void updateItem(long id, String number) throws SQLException, IOException, ClassNotFoundException {
        String sql = "UPDATE agenda_item SET phone_number=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, number);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteItem(long id) throws SQLException, IOException, ClassNotFoundException {
        String sql = "DELETE FROM agenda_item WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<AgendaItem> getAgendaItems(String firstName) throws SQLException, IOException, ClassNotFoundException {

        String sql = "SELECT id, phone_number, first_name, last_name FROM agenda_item WHERE first_name=?";
        List<AgendaItem> agendaItems = new ArrayList<>();

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, firstName);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AgendaItem agendaItem = new AgendaItem();
                agendaItem.setId(resultSet.getLong("id"));
                agendaItem.setFirstName(resultSet.getString("first_name"));
                agendaItem.setLastName(resultSet.getString("last_name"));
                agendaItem.setNumber(resultSet.getString("phone_number"));
                agendaItems.add(agendaItem);
            }
        }

        return agendaItems;
        }
    }

