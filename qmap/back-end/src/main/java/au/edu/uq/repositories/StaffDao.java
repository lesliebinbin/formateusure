package au.edu.uq.repositories;

public interface StaffDao {
    String getEmailByName(String name);

    String getNameById(String id);
}
