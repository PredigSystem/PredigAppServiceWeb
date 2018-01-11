package controllers;

import db.DBConnection;
import domain.BloodPressure;

import java.util.Date;
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
                            resultSet.getDate("date").getTime(),
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
                        resultSet.getDate("date").getTime(),
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
            if(bloodPressure.getDate() == null) bloodPressure.setDate(new Date().getTime());

            //TODO: Check ID if exists
            statement.setString(1, bloodPressure.generateId());
            statement.setString(2, bloodPressure.getUserId());
            statement.setDate(3, new java.sql.Date(bloodPressure.getDate()));
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
    
    public static String[] getBloodPressureSystolic(String userId) {
		String array[] = new String [10];
        for(int i = 0; i<10; i++) array[i] = "0";
    		
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT systolic FROM public.bloodpressure WHERE bloodpressure.userid = '" + userId + "' order by bloodpressure.date limit 10;")) {

            try (ResultSet resultSet = statement.executeQuery()) {
            		int i = 0;
                while (resultSet.next() && i < 10) {
                		array[i] = String.valueOf(resultSet.getDouble("systolic"));
                		i++;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return array;
    }
    
    public static String[] getBloodPressureDiastolic(String userId) {
		String array[] = new String [10];
        for(int i = 0; i<10; i++) array[i] = "0";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT diastolic FROM public.bloodpressure WHERE bloodpressure.userid = '" + userId + "' order by bloodpressure.date limit 10;")) {

            try (ResultSet resultSet = statement.executeQuery()) {
            		int i = 0;
                while (resultSet.next() && i < 10) {
                		array[i] = String.valueOf(resultSet.getDouble("diastolic"));
                		i++;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return array;
    }
    
    public static String[] getBloodPressurePulse(String userId) {
		String array[] = new String [3];
		Date aux = new Date();
		String date = String.valueOf(aux.getDay()) +"/"+ String.valueOf(aux.getMonth()) +"/"+String.valueOf(aux.getYear()-1);
		Date sql_date = new Date(date);
		int total = 0;
		int normal = 0;
		int regular = 0;
		int bad = 0;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT systolic, diastolic FROM public.bloodpressure WHERE bloodpressure.userid = '" + userId + "' and bloodpressure.date >'"+sql_date+"' order by bloodpressure.date;")) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                		if(resultSet.getDouble("systolic") < 130 && resultSet.getDouble("diastolic") < 80) {
                			normal++;
                			
                		} else if( (resultSet.getDouble("systolic") > 130 && resultSet.getDouble("systolic") < 180) || (resultSet.getDouble("diastolic") > 80 && resultSet.getDouble("diastolic") < 90 ) ) {
                			regular++;
                		} else {
                			bad++;
                		}

                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        total = normal + regular + bad;
        
        if(total < 0){
            array[0] = String.valueOf( (normal * 100) / total );
            array[1] = String.valueOf( (regular * 100) / total );
            array[2] = String.valueOf( (bad * 100) / total );
        }else{
            array[0] = "100";
            array[1] = "0";
            array[2] = "0";
        }

        return array;
    }
}
