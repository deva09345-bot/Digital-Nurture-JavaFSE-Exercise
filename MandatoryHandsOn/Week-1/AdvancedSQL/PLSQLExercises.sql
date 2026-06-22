-- ================================================================
-- Week-1 Advanced SQL / PL/SQL Exercises
-- Database: Oracle
-- ================================================================

-- ================================================================
-- SCHEMA SETUP
-- ================================================================

CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    DOB          DATE,
    Balance      NUMBER,
    LastModified DATE,
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER REFERENCES Customers(CustomerID),
    AccountType  VARCHAR2(20),
    Balance      NUMBER,
    LastModified DATE
);

CREATE TABLE Transactions (
    TransactionID   NUMBER PRIMARY KEY,
    AccountID       NUMBER REFERENCES Accounts(AccountID),
    TransactionDate DATE,
    Amount          NUMBER,
    TransactionType VARCHAR2(10)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER REFERENCES Customers(CustomerID),
    LoanAmount   NUMBER,
    InterestRate NUMBER,
    StartDate    DATE,
    EndDate      DATE
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name       VARCHAR2(100),
    Position   VARCHAR2(50),
    Salary     NUMBER,
    Department VARCHAR2(50),
    HireDate   DATE
);

CREATE TABLE AuditLog (
    LogID         NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    LogDate       DATE,
    LogMessage    VARCHAR2(255)
);

CREATE SEQUENCE AuditLog_seq START WITH 1 INCREMENT BY 1;

-- Sample Data
INSERT INTO Customers VALUES (1, 'John Doe',   TO_DATE('1955-03-10','YYYY-MM-DD'), 12000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-07-20','YYYY-MM-DD'),  8000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (3, 'Bob Brown',  TO_DATE('1950-01-15','YYYY-MM-DD'), 15000, SYSDATE, 'FALSE');

INSERT INTO Accounts VALUES (1, 1, 'Savings',  12000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking',  8000, SYSDATE);
INSERT INTO Accounts VALUES (3, 3, 'Savings',  15000, SYSDATE);

INSERT INTO Loans VALUES (1, 1, 50000, 5.5, SYSDATE, SYSDATE + 25);
INSERT INTO Loans VALUES (2, 2, 20000, 7.0, SYSDATE, SYSDATE + 10);

INSERT INTO Employees VALUES (1, 'Alice', 'Manager',   80000, 'IT',      TO_DATE('2020-01-10','YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob',   'Developer', 65000, 'IT',      TO_DATE('2021-06-01','YYYY-MM-DD'));
INSERT INTO Employees VALUES (3, 'Carol', 'Analyst',   55000, 'Finance', TO_DATE('2019-03-15','YYYY-MM-DD'));

COMMIT;

-- ================================================================
-- EXERCISE 1: CONTROL STRUCTURES
-- ================================================================

-- Scenario 1: Apply 1% discount to loan interest for customers above 60
DECLARE
    v_customer_id Customers.CustomerID%TYPE;
    v_dob         Customers.DOB%TYPE;
    v_age         NUMBER;
    CURSOR c_customers IS SELECT CustomerID, DOB FROM Customers;
BEGIN
    OPEN c_customers;
    LOOP
        FETCH c_customers INTO v_customer_id, v_dob;
        EXIT WHEN c_customers%NOTFOUND;
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, v_dob) / 12);
        IF v_age > 60 THEN
            UPDATE Loans
            SET    InterestRate = InterestRate - 1
            WHERE  CustomerID = v_customer_id
            AND    InterestRate > 1;
            DBMS_OUTPUT.PUT_LINE('Discount applied to CustomerID: ' || v_customer_id);
        END IF;
    END LOOP;
    CLOSE c_customers;
    COMMIT;
END;
/

-- Scenario 2: Set IsVIP = TRUE for customers with balance > 10000
DECLARE
    CURSOR c_customers IS SELECT CustomerID, Balance FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE('VIP flag set for CustomerID: ' || rec.CustomerID);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Print reminders for loans due within 30 days
DECLARE
    CURSOR c_loans IS
        SELECT l.LoanID, c.Name, l.EndDate
        FROM   Loans l
        JOIN   Customers c ON l.CustomerID = c.CustomerID
        WHERE  l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: ' || rec.Name ||
                             ' - LoanID ' || rec.LoanID ||
                             ' due on '   || TO_CHAR(rec.EndDate, 'DD-MON-YYYY'));
    END LOOP;
END;
/

-- ================================================================
-- EXERCISE 2: ERROR HANDLING
-- ================================================================

-- Scenario 1: SafeTransferFunds
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account);
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of $' || p_amount || ' completed successfully.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

-- Scenario 2: UpdateSalary
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage  IN NUMBER
) AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM Employees WHERE EmployeeID = p_employee_id;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID ' || p_employee_id || ' does not exist.');
    END IF;
    UPDATE Employees
    SET    Salary = Salary + (Salary * p_percentage / 100)
    WHERE  EmployeeID = p_employee_id;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for EmployeeID: ' || p_employee_id);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

-- Scenario 3: AddNewCustomer
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id      IN NUMBER,
    p_name    IN VARCHAR2,
    p_dob     IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_id || ' already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

-- ================================================================
-- EXERCISE 3: STORED PROCEDURES
-- ================================================================

-- Scenario 1: ProcessMonthlyInterest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET    Balance      = Balance * 1.01,
           LastModified = SYSDATE
    WHERE  AccountType  = 'Savings';
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END;
/

-- Scenario 2: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department    IN VARCHAR2,
    p_bonus_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET    Salary = Salary + (Salary * p_bonus_percent / 100)
    WHERE  Department = p_department;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus applied to department: ' || p_department);
END;
/

-- Scenario 3: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account;
    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance.');
    END IF;
    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from_account;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to_account;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred $' || p_amount || ' successfully.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed: ' || SQLERRM);
END;
/

-- ================================================================
-- EXERCISE 4: FUNCTIONS
-- ================================================================

-- Scenario 1: CalculateAge
CREATE OR REPLACE FUNCTION CalculateAge(p_dob IN DATE)
RETURN NUMBER AS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

-- Scenario 2: CalculateMonthlyInstallment (EMI)
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount   IN NUMBER,
    p_interest_rate IN NUMBER,
    p_years         IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_emi          NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / (12 * 100);
    v_months       := p_years * 12;
    IF v_monthly_rate = 0 THEN
        RETURN ROUND(p_loan_amount / v_months, 2);
    END IF;
    v_emi := p_loan_amount
             * v_monthly_rate
             * POWER(1 + v_monthly_rate, v_months)
             / (POWER(1 + v_monthly_rate, v_months) - 1);
    RETURN ROUND(v_emi, 2);
END;
/

-- Scenario 3: HasSufficientBalance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN VARCHAR2 AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'FALSE';
END;
/

-- ================================================================
-- EXERCISE 5: TRIGGERS
-- ================================================================

-- Scenario 1: UpdateCustomerLastModified
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Scenario 2: LogTransaction
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(LogID, TransactionID, LogDate, LogMessage)
    VALUES (
        AuditLog_seq.NEXTVAL,
        :NEW.TransactionID,
        SYSDATE,
        'Transaction: ID=' || :NEW.TransactionID ||
        ' Amount=$'        || :NEW.Amount ||
        ' Type='           || :NEW.TransactionType
    );
END;
/

-- Scenario 3: CheckTransactionRules
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'DEBIT' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20004, 'Withdrawal exceeds account balance.');
        END IF;
    ELSIF :NEW.TransactionType = 'CREDIT' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Deposit amount must be positive.');
    END IF;
END;
/

-- ================================================================
-- EXERCISE 6: CURSORS
-- ================================================================

-- Scenario 1: GenerateMonthlyStatements
DECLARE
    CURSOR c_statements IS
        SELECT c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM   Transactions t
        JOIN   Accounts a  ON t.AccountID  = a.AccountID
        JOIN   Customers c ON a.CustomerID = c.CustomerID
        WHERE  EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND    EXTRACT(YEAR  FROM t.TransactionDate) = EXTRACT(YEAR  FROM SYSDATE);
BEGIN
    FOR rec IN c_statements LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Customer: '  || rec.Name ||
            ' | Date: '   || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY') ||
            ' | Type: '   || rec.TransactionType ||
            ' | Amount: $' || rec.Amount
        );
    END LOOP;
END;
/

-- Scenario 2: ApplyAnnualFee
DECLARE
    v_annual_fee CONSTANT NUMBER := 50;
    CURSOR c_accounts IS SELECT AccountID, Balance FROM Accounts FOR UPDATE;
BEGIN
    FOR rec IN c_accounts LOOP
        UPDATE Accounts
        SET    Balance      = rec.Balance - v_annual_fee,
               LastModified = SYSDATE
        WHERE  CURRENT OF c_accounts;
        DBMS_OUTPUT.PUT_LINE('Annual fee deducted from AccountID: ' || rec.AccountID);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: UpdateLoanInterestRates
DECLARE
    CURSOR c_loans IS SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
    v_new_rate NUMBER;
BEGIN
    FOR rec IN c_loans LOOP
        v_new_rate := rec.InterestRate + 0.5;
        UPDATE Loans
        SET    InterestRate = v_new_rate
        WHERE  CURRENT OF c_loans;
        DBMS_OUTPUT.PUT_LINE('LoanID ' || rec.LoanID || ' new rate: ' || v_new_rate);
    END LOOP;
    COMMIT;
END;
/

-- ================================================================
-- EXERCISE 7: PACKAGES
-- ================================================================

-- Scenario 1: CustomerManagement Package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER);
    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_balance IN NUMBER);
    FUNCTION  GetCustomerBalance(p_id IN NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER) AS
    BEGIN
        INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: CustomerID ' || p_id || ' already exists.');
    END;

    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_balance IN NUMBER) AS
    BEGIN
        UPDATE Customers SET Name = p_name, Balance = p_balance WHERE CustomerID = p_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer updated: ' || p_id);
    END;

    FUNCTION GetCustomerBalance(p_id IN NUMBER) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END;

END CustomerManagement;
/

-- Scenario 2: EmployeeManagement Package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2,
                           p_salary IN NUMBER, p_dept IN VARCHAR2);
    PROCEDURE UpdateEmployee(p_id IN NUMBER, p_salary IN NUMBER, p_pos IN VARCHAR2);
    FUNCTION  GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2,
                           p_salary IN NUMBER, p_dept IN VARCHAR2) AS
    BEGIN
        INSERT INTO Employees VALUES (p_id, p_name, p_pos, p_salary, p_dept, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Hired: ' || p_name);
    END;

    PROCEDURE UpdateEmployee(p_id IN NUMBER, p_salary IN NUMBER, p_pos IN VARCHAR2) AS
    BEGIN
        UPDATE Employees SET Salary = p_salary, Position = p_pos WHERE EmployeeID = p_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Updated EmployeeID: ' || p_id);
    END;

    FUNCTION GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary * 12 INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END;

END EmployeeManagement;
/

-- Scenario 3: AccountOperations Package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_account_id IN NUMBER, p_customer_id IN NUMBER,
                          p_type IN VARCHAR2, p_balance IN NUMBER);
    PROCEDURE CloseAccount(p_account_id IN NUMBER);
    FUNCTION  GetTotalBalance(p_customer_id IN NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(p_account_id IN NUMBER, p_customer_id IN NUMBER,
                          p_type IN VARCHAR2, p_balance IN NUMBER) AS
    BEGIN
        INSERT INTO Accounts VALUES (p_account_id, p_customer_id, p_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account opened: ' || p_account_id);
    END;

    PROCEDURE CloseAccount(p_account_id IN NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account closed: ' || p_account_id);
    END;

    FUNCTION GetTotalBalance(p_customer_id IN NUMBER) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM   Accounts
        WHERE  CustomerID = p_customer_id;
        RETURN v_total;
    END;

END AccountOperations;
/

-- ================================================================
-- TEST CALLS
-- ================================================================
-- EXEC SafeTransferFunds(1, 2, 500);
-- EXEC UpdateSalary(1, 10);
-- EXEC AddNewCustomer(10, 'New User', TO_DATE('1995-05-05','YYYY-MM-DD'), 5000);
-- EXEC ProcessMonthlyInterest;
-- EXEC UpdateEmployeeBonus('IT', 15);
-- EXEC TransferFunds(1, 2, 1000);
-- SELECT CalculateAge(TO_DATE('1980-06-15','YYYY-MM-DD')) FROM DUAL;
-- SELECT CalculateMonthlyInstallment(100000, 8.5, 5) FROM DUAL;
-- SELECT HasSufficientBalance(1, 5000) FROM DUAL;
-- EXEC CustomerManagement.AddCustomer(20,'Test',TO_DATE('2000-01-01','YYYY-MM-DD'),3000);
-- SELECT CustomerManagement.GetCustomerBalance(1) FROM DUAL;
-- SELECT EmployeeManagement.GetAnnualSalary(1) FROM DUAL;
-- SELECT AccountOperations.GetTotalBalance(1) FROM DUAL;
