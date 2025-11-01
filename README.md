# MoneyMaestro
A cutting-edge expense management app designed for today's fast-paced world. It simplifies personal finance by helping users track expenses, set budgets, and achieve financial goals. 
With its user-friendly interface, real-time insights, and personalized advice, MoneyMaestro empowers individuals to take control of their finances, reducing stress and making financial wellness accessible to all.

##Problem Statement:
Many individuals struggle with tracking daily expenses and managing personal finances effectively, leading to poor budgeting and financial stress. A user-friendly digital solution is needed to help people monitor spending, set budgets, and analyze their financial habits.

##OBJECTIVES:
1. Track and categorize expenses.
2. Set and monitor budgets.
3. Provide financial insights through charts and reports.
4. Send notifications and reminders.
   Support multiple platforms.

##SIGNUP/LOGIN PAGE 

<img width="822" height="558" alt="image" src="https://github.com/user-attachments/assets/047bbca1-c8de-4611-a8e8-d5e619f20ed7" />
<img width="796" height="464" alt="image" src="https://github.com/user-attachments/assets/a7b472bb-8000-4231-9a56-c93591ebe4ca" />

##ADD EXPENSE PAGE - 
Allows users to add daily expenses by selecting a date, entering an amount, and choosing a category. It also displays the last 20 days of spending with details like ID, date, category, and amount, along with the total spent.

<img width="883" height="620" alt="image" src="https://github.com/user-attachments/assets/9d63d826-1251-4ba5-b25a-cf1173a595b2" />

##CATEGORY PAGE - 
Enables users to create and manage expense categories for better organization. Users can add new categories, view the list of existing ones, and delete or update them as needed. This ensures flexibility in customizing expense tracking.

<img width="888" height="705" alt="image" src="https://github.com/user-attachments/assets/4d168c77-3f12-4620-b58b-2dd07217868a" />

##DASHBOARD WITH ALL EXPENSES - View Expenses Page
Provides two filters for tracking spending:
> By Date Range (from–to)
> By Category (with optional date range)
Displays a list of expenses and calculates the total spent based on the selected filter.

<img width="906" height="680" alt="image" src="https://github.com/user-attachments/assets/72beb836-a07d-4423-a624-53773d867f98" />

##FAMILY EXPENSE - 
Lets users record expenses for individual family members by selecting a member, entering category and amount. It shows a summary table with category, amount, and member details, with an option to delete records.

<img width="950" height="672" alt="image" src="https://github.com/user-attachments/assets/e75a76e4-e6f8-41d2-84b6-92c56c010c87" />


## How to build and run

MoneyMaestro is a Java Swing desktop app that uses MySQL and two third‑party libraries:

- JFreeChart (charts)
- JCalendar (date chooser)

The repository already includes the required JARs in `lib/` and the app’s image resources in `resources/`. Follow the steps below for your environment.

### Prerequisites

- JDK 11 or newer (javac/java available on PATH)
- MySQL running locally (or reachable), with a database named `mm1`
- Optional: VS Code Dev Container users can run headless using Xvfb (see Troubleshooting)

### Database setup

The app expects MySQL at `jdbc:mysql://localhost:3306/mm1` with user `root` and password `trisha2005` as currently hard-coded in:

- `database connection/DbConnect.java`
- `java/login.java`
- `java/Signup.java`
- `java/ExpenseTracker.java`, `java/Category.java`, `java/ViewSpending.java`, `java/report.java`, `java/Graph.java`

You can change these credentials/URL in code to match your environment. Create the schema and tables:

```sql
CREATE DATABASE IF NOT EXISTS mm1;
USE mm1;

-- user accounts for app login/signup
CREATE TABLE IF NOT EXISTS users (
   id INT AUTO_INCREMENT PRIMARY KEY,
   fullname VARCHAR(100) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL
);

-- categories (unique names)
CREATE TABLE IF NOT EXISTS category_info (
   category VARCHAR(100) PRIMARY KEY
);

-- spendings entries
CREATE TABLE IF NOT EXISTS spendings (
   s_id INT AUTO_INCREMENT PRIMARY KEY,
   category VARCHAR(100) NOT NULL,
   date DATE NOT NULL,
   amount INT NOT NULL,
   CONSTRAINT fk_spendings_category
      FOREIGN KEY (category) REFERENCES category_info(category)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

-- sample data (optional)
INSERT IGNORE INTO category_info(category) VALUES ('Food'),('Transport'),('Utilities');
INSERT INTO users(fullname,email,password) VALUES ('Demo User','demo@example.com','demo123')
ON DUPLICATE KEY UPDATE fullname=VALUES(fullname);
```

### Build

From the project root:

```bash
# Compile all sources into build/classes
javac -d build/classes -cp "lib/*" java/*.java "database connection/DbConnect.java"
```

### Run

Launch the login window:

```bash
java -cp "build/classes:resources:lib/*" mm.login
```

- On Windows PowerShell/CMD, use `;` as the classpath separator:

```powershell
java -cp "build/classes;resources;lib/*" mm.login
```

### Troubleshooting

- Headless/containers (no desktop available): run under a virtual X server.

```bash
sudo apt-get update
sudo apt-get install -y xvfb libxext6 libxrender1 libxtst6 libxi6 libfreetype6 fonts-dejavu-core
xvfb-run -a java -cp "build/classes:resources:lib/*" mm.login
```

- UnsatisfiedLinkError mentioning `libawt_xawt.so` or `libXtst.so.6`: install the packages above (`libxtst6`, `libxext6`, etc.).

- MySQL connection errors: verify the DB is running and credentials/URL match your setup. Update the JDBC URL/credentials in the Java files listed under “Database setup.”

- Missing image `/icon/money.jpg`: ensure `resources/icon/money.jpg` exists. The repo places the image there already.

### Security note

The current code uses hard-coded database credentials for development/demo purposes. For production, externalize secrets (environment variables or config files) and use hashed passwords.
