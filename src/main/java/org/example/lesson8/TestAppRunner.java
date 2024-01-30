package org.example.lesson8;

import com.google.gson.reflect.TypeToken;
import org.example.lesson8.connection.SQLConnection;
import org.example.lesson8.dao.DAO;
import org.example.lesson8.dao.DoorDAO;
import org.example.lesson8.dao.impl.DAOImpl;
import org.example.lesson8.dao.impl.DoorDAOImpl;
import org.example.lesson8.dto.DoorDTO;
import org.example.lesson8.utils.GsonManager;
import org.example.lesson8.utils.wrappers.ThrowingConsumerWrapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.example.lesson8.utils.Constants.*;

public class TestAppRunner {
    private static final GsonManager GSON_MANAGER = new GsonManager();
    private static final DoorDAO DOORS_DAO = new DoorDAOImpl();
    private static final DAO<DoorDTO> DAO = new DAOImpl<>();  // ??????????? DoorDAOImpl include DAOIml<> methods. This line so useless.

    public static void main(String[] args) {
        runner("doors", DoorDTO.class, new DoorDAOImpl());
    }

    private Type setModelAndGetCorrespondingList2(Class<?> typeArgument) {
        return TypeToken.getParameterized(typeArgument).getType();
    }

    private static void runner(String filename, Class<?> clazz, DAOImpl<?> DAO ){
        try {



            /*DAO<clazz> DAO_RUNNER = new DAOImpl<>(); */ // Any ideas how to select current impl. Now I try to use hack.
            //Work good!
            String path = IN_FILE_PATH.replaceFirst("\\?", filename);
            var DTOList = GSON_MANAGER.readDTOList(path, clazz);

            System.out.println("List before save:");
            DTOList.forEach(System.out::println);
            // end

            List<?> dtoAfterSave = new ArrayList<>();
            DTOList.forEach(ThrowingConsumerWrapper.accept(dto -> dtoAfterSave.add(DAO.save(dto, clazz)), SQLException.class));

            if (!dtoAfterSave.isEmpty()) {
                System.out.println("\nList after save: ");
                dtoAfterSave.forEach(System.out::println);

                int randomId = RANDOM.nextInt(dtoAfterSave.size());

                DoorDTO objectForUpdate = DAO.get(dtoAfterSave.get(randomId).getId(), DoorDTO.class);
                System.out.println("Get object by id: " + dtoAfterSave.get(randomId).getId() + ": " + objectForUpdate);
                System.out.println("Updated rows: " + DAO.update(objectForUpdate));
                System.out.println("Deleted rows: " + DAO.delete(dtoAfterSave.get(randomId).getId(), DoorDTO.class));

                List<DoorDTO> doors = DOORS_DAO.getBySize(900, 1300);
                if (doors != null && !doors.isEmpty()) {
                    System.out.println("Get by size from 900 to 1300: ");
                    doors.forEach(System.out::println);

                    GSON_MANAGER.writeDoorsDTOList(DOORS_OUT_FILE_PATH, doors);
                } else {
                    System.out.println("Sorry, nothing found");
                }
            }

            SQLConnection.closeConnection();

        } catch (IOException | SQLException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
