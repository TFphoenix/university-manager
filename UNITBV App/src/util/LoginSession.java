package util;

import daoimpl.ProfessorDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.UtilizatorDaoImpl;
import entities.Utilizator;

public class LoginSession {
    static private Utilizator user;
    static private String username;
    static private String password;
    static private int entityId;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginSession.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        LoginSession.password = password;
    }

    public static String getRights() {
        return user.getDrepturi();
    }

    public static int getId() {
        return user.getId();
    }

    public static int getEntityId() {
        return entityId;
    }

    public static boolean checkLoginCredentials() {

        if (username.equals("") || password.equals(""))
            return false;

        UtilizatorDaoImpl utilizatorDb = new UtilizatorDaoImpl();
        try {
            user = utilizatorDb.selectByMail(username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (user.getParola().equals(password)) {
                switch (user.getDrepturi()) {
                    case "student":
                        StudentDaoImpl sdi = new StudentDaoImpl();
                        entityId = sdi.selectByUserID(user.getId()).getId();
                        break;
                    case "profesor":
                        ProfessorDaoImpl pdi = new ProfessorDaoImpl();
                        entityId = pdi.selectByUserID(user.getId()).getId();
                        break;
                    default:
                        entityId = 0;
                }
                return true;
            }
        }
        return false;
    }
}
