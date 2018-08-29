
package ua.training.model.dao;

import ua.training.model.entity.Check;

import java.time.LocalDate;
import java.util.List;

public interface CheckDao extends AutoCloseable {
    void createCheck(Check check);
    Check findCheckById(int id);
    List<Check> findAll();
    List<Check> findAll(int limit, int offset);
    List<Check> findAll(LocalDate from, LocalDate to);
    List<Check> findAllSumReport(int from, int to);
    void deleteCheck(int id);
    int getLatestCheckId();
    void deleteProductFromCheck(int checkId, int productId);
    int getNumberOfRecords();
}