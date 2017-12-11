package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import domain.BloodPressure;
import domain.VisitsDoctor;

public class VisitsDoctorController {
	
    public static List<VisitsDoctor> getVisitsDoctorById(String userId){
        List<VisitsDoctor> visitsDoctorList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.visits_doctor WHERE visits_doctor.userid = '" + userId + "';")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                		visitsDoctorList.add(new VisitsDoctor(
                            userId,
                            resultSet.getString("doctor"),
                            resultSet.getDate("date"),
                            resultSet.getTime("time"),
                            resultSet.getString("reason")
                    ));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return visitsDoctorList;
    }
    
    
    public static VisitsDoctor getLastVisitsDoctorByUserId(String userId){
        VisitsDoctor visitsDoctor = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.visits_doctor WHERE visits_doctor.userid = '" + userId + "' order by visits_doctor.date desc limit 1;")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                visitsDoctor  = new VisitsDoctor(
                        userId,
                        resultSet.getString("doctor"),
                        resultSet.getDate("date"),
                        resultSet.getTime("time"),
                        resultSet.getString("reason")
                );

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return visitsDoctor;
    }

}
