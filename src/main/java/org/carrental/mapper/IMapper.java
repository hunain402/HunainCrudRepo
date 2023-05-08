package org.carrental.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**I MAPPER USE TO CONVERT RESULT SET DATA INTO LIST OR IN A SINGLE LIST OBJECT
 GENERIC METHOD (T IS TYPE)*/

public interface IMapper<T> {
 List<T> resultSetToList(ResultSet rs) throws SQLException;

 T resultSetToListObject(ResultSet rs) throws SQLException;
}
