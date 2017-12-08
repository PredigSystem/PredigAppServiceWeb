package controllers;

import db.DBConnection;
import domain.BloodPressure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
