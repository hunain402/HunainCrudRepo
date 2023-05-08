package org.carrental.dao;

import org.carrental.UI.MonthlyUi;

public class SqlQueryConstant {

//                                                          CUSTOMER QUERY
    public final static String INSERT_INTO_CUSTOMER = "insert into customer (customer_name,customer_number," +
        "customer_cnic,customer_address,customer_ref_number) " +
            "values(?,?,?,?,?)";
    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String GET_ALL_CUSTOMER_EXCEPT_DELETE = "select * from customer where status <> 'deleted'";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id = ?";
    public final static  String UPDATE_CUSTOMER_BY_ID = "update customer set customer_name = ?,customer_number = ?," +
            "customer_cnic = ?, customer_address = ?, customer_ref_number =? where id = ? ";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id = ?";
    public final static String UPDATE_CUSTOMER_IS_DELETED_BY_ID = "UPDATE customer SET status = 'deleted' WHERE id = ?";


//                                                    BOOKING QUERY
    public final static String INSERT_INTO_BOOKING = "insert into booking (price,Vehicle_id,booking_date,customer_id,status) values(?,?,?,?,'booked')";
    public final static String GET_ALL_BOOKING = "select * from booking b inner join customer c on c.id = b.customer_id inner join vehicle v on v.id = b.vehicle_id;";

    public final static String GET_ALL_CID = "select customer_id from booking";
    public final static String GET_BOOKING_BY_ID = "select * from booking where id = ?";
    public final static  String UPDATE_BOOKING_BY_ID = "update booking set price = ? where id = ? ";
    public final static String DELETE_BOOKING_BY_ID = "UPDATE booking SET status = 'deleted' WHERE id = ?";
    MonthlyUi monthlyUi = new MonthlyUi();
    public final static String GET_ALL_30DAYS_BOOKING ="select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.end_date,\n" +
            "             b.price * DATEDIFF(end_date,booking_date) as total_amount, b.price, DATEDIFF(b.end_date,b.booking_date) \n" +
            "            as noOfDays, b.status, c.customer_name, v.vehicle_name from booking b\n" +
            "             inner join customer c on b.customer_id = c.id inner join vehicle v on b.vehicle_id = v.id where b.booking_date\n" +
            "             BETWEEN ? AND ?  AND b.status='Completed'";
    public final static String  GET_ALL_COMMISION ="select Sum(o.owner_commision*(DATEDIFF(b.end_date , b.booking_date)*b.price)/100)" +
            " commision from booking b inner join vehicle v on v.id=b.vehicle_id inner join " +
            "vehicle_owner o on o.id = v.owner_id where (b.booking_date Between ? And ?)";

    public final static String  GET_ALL_COMMISION_OF_EACH_OWNER="SELECT  vo.owner_name,vo.owner_commision ," +
            "round(SUM(b.price * (DATEDIFF(b.end_date, b.booking_date))) * (vo.owner_commision / 100),2) as commision\n" +
            "FROM booking b \n" +
            "INNER JOIN vehicle v ON b.vehicle_id = v.id \n" +
            "INNER JOIN vehicle_owner vo ON v.owner_id = vo.id \n" +
            "WHERE b.booking_date BETWEEN ? AND ? \n" +
            "GROUP BY vo.id, vo.owner_name \n" +
            "ORDER BY owner_commision DESC\n";

    public final static String GET_ALL_BOOKING_DETAIL = "select b.id,b.price,b.status,b.booking_date,end_date,c.customer_name," +
            "v.vehicle_name,c.id,v.id from booking b inner join customer c on c.id = b.customer_id inner join" +
            " vehicle v on v.id = b.vehicle_id WHERE b.status <> 'deleted';";

    public final static String GET_HIGHEST_REVENUE_CAR_OF_THE_MONTH = "SELECT v.vehicle_name, b.vehicle_id  \n" +
            "FROM booking b \n" +
            "INNER JOIN vehicle v ON b.vehicle_id = v.id \n" +
            "INNER JOIN vehicle_owner vo ON vo.id = v.owner_id\n" +
            "WHERE b.status = 'completed' \n" +
            "AND b.booking_date BETWEEN ? AND ? \n" +
            "ORDER BY (b.price*datediff(b.end_date,b.booking_date))-((b.price*datediff(b.end_date,b.booking_date))*(vo.owner_commision))/100 DESC \n" +
            "LIMIT 1;";
    public final static String MOST_FREQUENT_CAR=" SELECT v.vehicle_name,b.vehicle_id\n" +
            "FROM booking b\n" +
            "INNER JOIN vehicle v ON b.vehicle_id = v.id\n" +
            "WHERE b.booking_date BETWEEN ? AND ? \n" +
            "GROUP BY v.id, v.vehicle_name\n" +
            "ORDER BY COUNT(*) DESC\n" +
            "LIMIT 1;";
    public final static String LEAST_FREQUENT_CAR="SELECT v.vehicle_name, b.vehicle_id\n" +
            "FROM booking b\n" +
            "INNER JOIN vehicle v ON b.vehicle_id = v.id\n" +
            "WHERE b.booking_date BETWEEN ? AND ?\n" +
            "GROUP BY v.id, v.vehicle_name\n" +
            "HAVING COUNT(*) = (\n" +
            "  SELECT MIN(cnt)\n" +
            "  FROM (\n" +
            "    SELECT COUNT(*) AS cnt\n" +
            "    FROM booking b\n" +
            "    INNER JOIN vehicle v ON b.vehicle_id = v.id\n" +
            "    WHERE b.booking_date BETWEEN ? AND ? \n" +
            "    GROUP BY v.id, v.vehicle_name\n" +
            "  ) t\n" +
            ")\n" +
            "\n";

    public final static String MAX_COMMISON_OF_THE_MONTH=" SELECT v.owner_id, vo.owner_name \n" +
            "FROM booking b \n" +
            "INNER JOIN vehicle v ON b.vehicle_id = v.id \n" +
            "INNER JOIN vehicle_owner vo ON v.owner_id = vo.id \n" +
            "WHERE b.booking_date BETWEEN ? AND ? \n" +
            "GROUP BY vo.id, vo.owner_name \n" +
            "ORDER BY SUM(b.price * (DATEDIFF(b.end_date, b.booking_date))) * (vo.owner_commision / 100) DESC \n" +
            "LIMIT 1;\n";

    public final static String TO_COMPLETE_BOOKING_DATE_SATUS="";




    //                                                VEHICLE QUERY
    public final static String INSERT_INTO_VEHICLE = "insert into vehicle (vehicle_name,vehicle_model,vehicle_brand,vehicle_colour,owner_id) " +
            "values(?,?,?,?,?) ";
    public final static String GET_ALL_VEHICLE= "select * from vehicle";
        public final static String GET_VEHICLE_BY_ID = "select * from vehicle where id = ?";

    public final static  String UPDATE_VEHICLE_BY_ID ="update vehicle set vehicle_name = ?, vehicle_model=?, " +
            "vehicle_brand =?,vehicle_colour =?,owner_id = ? where id = ?";
      public final static String UPDATE_VEHILCE_DELETE_BY_ID = "UPDATE vehicle SET status = 'deleted' WHERE id = ?";

//                                                    OWNER QUERY
  public final static String INSERT_INTO_VEHICLE_OWNER = "insert into vehicle_owner (owner_name,owner_cnic,owner_number,owner_address,owner_commision) values(?,?,?,?,?)";

    public final static String GET_ALL_FROM_OWNER = "select * from vehicle_owner";

    public final static String GET_OWNER_BY_ID = "select * from vehicle_owner where id = ?";

    public final static  String UPDATE_OWNER_BY_ID = "update vehicle_owner set owner_name = ?,owner_cnic = ? where id = ? ";

    public final static String DELETE_OWNER_BY_ID = "delete from vehicle_owner where id = ?";

    public final static  String GET_ALL_DATA_WITH_SELECTED_YEAR_AND_OWNER="SELECT o.owner_name,o.owner_commision as commision_percent,\n" +
            " v.vehicle_name,b.booking_date,b.end_date,\n" +
            " o.owner_commision*(DATEDIFF(b.end_date , b.booking_date)*b.price)/100 as commission FROM  vehicle_owner o \n" +
            "inner join vehicle v on o.id = v.owner_id \n" +
            "inner join booking b on b.vehicle_id = v.id where end_date \n" +
            "between '2023-01-01' and '2023-12-31' and b.status = 'Completed' and o.owner_name =?;";

//                                               USER QUERY

    public final static String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from user where user_name = ? AND pass = ? ";

}
