package com.bajajfinserv.service;

import org.springframework.stereotype.Service;

@Service
public class SqlQueryBuilder {

    /**
     * Builds the SQL query to solve the problem:
     * For every department, calculate the average age of individuals with salaries exceeding ₹70,000,
     * and produce a concatenated string containing at most 10 of their names.
     * 
     * Output:
     * - DEPARTMENT_NAME: The name of the department
     * - AVERAGE_AGE: The average age of employees earning more than ₹70,000
     * - EMPLOYEE_LIST: Comma-separated list of up to 10 employee names (FIRST_NAME LAST_NAME)
     * 
     * Ordered by department ID in descending order.
     */
    public String buildQuery() {
        return "SELECT " +
               "    d.DEPARTMENT_NAME, " +
               "    ROUND(AVG(YEAR(CURRENT_DATE) - YEAR(e.DOB) - " +
               "        (CASE WHEN MONTH(CURRENT_DATE) < MONTH(e.DOB) " +
               "              OR (MONTH(CURRENT_DATE) = MONTH(e.DOB) AND DAY(CURRENT_DATE) < DAY(e.DOB)) " +
               "         THEN 1 ELSE 0 END)), 2) AS AVERAGE_AGE, " +
               "    SUBSTRING_INDEX(" +
               "        GROUP_CONCAT(DISTINCT CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) " +
               "            ORDER BY e.EMP_ID SEPARATOR ', '), " +
               "        ', ', " +
               "        9" +
               "    ) AS EMPLOYEE_LIST " +
               "FROM DEPARTMENT d " +
               "INNER JOIN EMPLOYEE e ON d.DEPARTMENT_ID = e.DEPARTMENT " +
               "INNER JOIN (" +
               "    SELECT DISTINCT EMP_ID " +
               "    FROM PAYMENTS " +
               "    WHERE AMOUNT > 70000" +
               ") p ON e.EMP_ID = p.EMP_ID " +
               "GROUP BY d.DEPARTMENT_ID, d.DEPARTMENT_NAME " +
               "ORDER BY d.DEPARTMENT_ID DESC";
    }
}

