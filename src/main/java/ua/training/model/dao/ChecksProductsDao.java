package ua.training.model.dao;

import ua.training.model.entity.Check;

public interface ChecksProductsDao extends AutoCloseable {
    void createCheck(Check check);
}
