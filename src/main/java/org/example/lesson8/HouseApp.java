package org.example.lesson8;

import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.HouseDAO;
import org.example.lesson8.dao.impl.HouseDAOImpl;
import org.example.lesson8.dto.HouseDTO;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.lesson8.utils.Constants.*;

public class HouseApp {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final HouseDAO HOUSE_DAO = new HouseDAOImpl();
    private static final DAO<HouseDTO> DAO = new DAOImpl<>();

    public static void main(String[] args) {/*
        try {
            List<HouseDTO> houseDTOList = GSON_MANAGER.readHousesDTOList(HOUSES_IN_FILE_PATH);
            System.out.println("List before save:");
            houseDTOList.forEach(System.out::println);

            List<HouseDTO> dtoAfterSave = new ArrayList<>();

            houseDTOList.forEach(ThrowingConsumerWrapper.accept(dto -> dtoAfterSave.add(DAO.save(dto, HouseDTO.class)), SQLException.class));

            if (!dtoAfterSave.isEmpty()) {
                System.out.println("\nList after save: ");
                dtoAfterSave.forEach(System.out::println);

                int randomId = RANDOM.nextInt(dtoAfterSave.size());

                HouseDTO objectForUpdate = DAO.get(dtoAfterSave.get(randomId).getId(), HouseDTO.class);
                System.out.println("Get object by id: " + dtoAfterSave.get(randomId).getId() + ": " + objectForUpdate);
                System.out.println("Updated rows: " + DAO.update(objectForUpdate));
                System.out.println("Deleted rows: " + DAO.delete(dtoAfterSave.get(randomId).getId(), HouseDTO.class));

                List<HouseDTO> houses = HOUSE_DAO.getByColor("белый");
                if (houses != null && !houses.isEmpty()) {
                    System.out.println("Get by color \"белый\": ");
                    houses.forEach(System.out::println);

                    GSON_MANAGER.writeHousesDTOList(HOUSES_OUT_FILE_PATH, houses);
                } else {
                    System.out.println("Sorry, nothing found");
                }
            }

            SQLConnection.closeConnection();

        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }*/
    }

}
