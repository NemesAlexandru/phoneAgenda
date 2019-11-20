package org.fasttrackit.phoneagenda.web;

import org.fasttrackit.phoneagenda.config.ObjectMapperConfiguration;
import org.fasttrackit.phoneagenda.domain.AgendaItem;
import org.fasttrackit.phoneagenda.service.PhoneAgendaService;
import org.fasttrackit.phoneagenda.transfer.CreateItemRequest;
import org.fasttrackit.phoneagenda.transfer.DeleteRequest;
import org.fasttrackit.phoneagenda.transfer.FilterRequest;
import org.fasttrackit.phoneagenda.transfer.UpdateItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/agenda-items")
public class PhoneAgendaServlet extends HttpServlet {

    private PhoneAgendaService phoneAgendaService = new PhoneAgendaService();

    //endpoint
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateItemRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), CreateItemRequest.class);

        try {
            phoneAgendaService.createItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String id = req.getParameter("id");

        DeleteRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), DeleteRequest.class);
        try {
            List<AgendaItem> deleteItems = phoneAgendaService.deleteItem(request);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        UpdateItemRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), UpdateItemRequest.class);

        try {
            phoneAgendaService.updateItem(Long.parseLong(id), request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FilterRequest request = ObjectMapperConfiguration.getObjectMapper().readValue(req.getReader(), FilterRequest.class);
        try {
            List<AgendaItem> agendaItems = phoneAgendaService.getAgendaItems(request);

            String response = ObjectMapperConfiguration.getObjectMapper().writeValueAsString(agendaItems);

            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500, "Internal Server Error: " + e.getMessage());
        }

    }


}
