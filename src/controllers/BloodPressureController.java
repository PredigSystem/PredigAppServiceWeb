package controllers;

import db.DBConnection;
import domain.BloodPressure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class BloodPressureController {

    public static List<BloodPressure> getBloodPressureByUserId(String userId){
        List<BloodPressure> bloodPressureList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.bloodpressure WHERE bloodpressure.userid = '" + userId + "';")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    bloodPressureList.add(new BloodPressure(
                            userId,
                            resultSet.getDate("date"),
                            resultSet.getDouble("latitude"),
                            resultSet.getDouble("longitude"),
                            resultSet.getDouble("systolic"),
                            resultSet.getDouble("diastolic"),
                            resultSet.getInt("pulse")
                    ));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return bloodPressureList;
    }

    public static BloodPressure getLastBloodPressureByUserId(String userId){
        BloodPressure bloodPressure = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.bloodpressure WHERE bloodpressure.userid = '" + userId + "' order by bloodpressure.date desc limit 1;")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                bloodPressure  = new BloodPressure(
                        userId,
                        resultSet.getDate("date"),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("systolic"),
                        resultSet.getDouble("diastolic"),
                        resultSet.getInt("pulse")
                );

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return bloodPressure;
    }

    public static BloodPressure create(BloodPressure bloodPressure) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO public.bloodpressure (id, userid, date, latitude, longitude, systolic, diastolic, pulse) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
            if(bloodPressure.getDate() == null) bloodPressure.setDate(new Date(new java.util.Date().getTime()));

            //TODO: Check ID if exists
            statement.setString(1, bloodPressure.generateId());
            statement.setString(2, bloodPressure.getUserId());
            statement.setDate(3, bloodPressure.getDate());
            statement.setDouble(4, bloodPressure.getLatitude());
            statement.setDouble(5, bloodPressure.getLongitude());
            statement.setDouble(6, bloodPressure.getSystolic());
            statement.setDouble(7, bloodPressure.getDiastolic());
            statement.setInt(8, bloodPressure.getPulse());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL Error on Create BloodPressur");
            System.err.println(e.getMessage());
            return null;
        }
        return bloodPressure;
    }
}
