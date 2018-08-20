package ua.training.model.dao;

import ua.training.model.entity.Check;

import java.util.List;

public interface CheckDao extends AutoCloseable {
    void createCheck(Check check);
    Check findCheckById(int id);
    List<Check> findAll();
    void updateCheck(Check check);
    void deleteCheck(int id);
    int getLatestCheckId();
}
