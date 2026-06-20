# Digital Nurture 5.0 – Deep Skilling exercise

> **Program:** Cognizant Digital Nurture 5.0  
> **Track:** Java Full Stack Engineer (Angular)  
> **Week:** 1

---

## Folder Structure

```
Week-1/
├── DesignPatterns/
│   ├── SingletonPatternExample/
│   ├── FactoryMethodPatternExample/
│   ├── BuilderPatternExample/
│   ├── AdapterPatternExample/
│   ├── DecoratorPatternExample/
│   ├── ProxyPatternExample/
│   ├── ObserverPatternExample/
│   ├── StrategyPatternExample/
│   ├── CommandPatternExample/
│   ├── MVCPatternExample/
│   └── DependencyInjectionExample/
├── DataStructures/
│   ├── InventoryManagementSystem/
│   ├── EcommerceSearch/
│   ├── SortingCustomerOrders/
│   ├── EmployeeManagementSystem/
│   ├── TaskManagementLinkedList/
│   ├── LibraryManagementSearch/
│   └── FinancialForecasting/
├── SpringCoreMaven/
│   └── LibraryManagement/
├── JUnitMockito/
│   ├── JUnitBasic/
│   ├── AdvancedJUnit/
│   └── MockitoExercises/
└── AdvancedSQL/
    └── PLSQLExercises.sql
```

## Module Summaries

| # | Module | Key Concepts |
|---|--------|-------------|
| 1 | Design Patterns | Singleton, Factory, Builder, Adapter, Decorator, Proxy, Observer, Strategy, Command, MVC, DI |
| 2 | Data Structures | HashMap, Arrays, LinkedList, Linear/Binary Search, Bubble/Quick Sort, Recursion |
| 3 | Spring Core & Maven | IoC, DI, AOP, Bean config (XML + Annotations), Spring Boot |
| 4 | JUnit / Mockito | Unit testing, Parameterized tests, Mocking, Stubbing, Verification |
| 5 | Advanced SQL | PL/SQL Control structures, Procedures, Functions, Triggers, Cursors, Packages |

## How to Run

### Design Patterns & Data Structures
Compile and run any file with:
```bash
javac *.java && java <TestClassName>
```

### Spring Core Maven
```bash
cd SpringCoreMaven/LibraryManagement
mvn clean install
mvn exec:java -Dexec.mainClass="com.library.LibraryManagementApplication"
```

### JUnit / Mockito
Add dependencies to your project (JUnit 5 + Mockito) then run:
```bash
mvn test
```

### PL/SQL
Run `PLSQLExercises.sql` in Oracle SQL Developer or SQL*Plus:
```sql
@PLSQLExercises.sql
```
