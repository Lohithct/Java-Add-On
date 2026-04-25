package com.jobportal;
import java.sql.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
   public static void main(String[] args) {
       try (Scanner sc = new Scanner(System.in)) {
           Connection con = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:orcl",
                   "scott",
                   "tiger"
           );
           while (true) {
               LocalDateTime now = LocalDateTime.now();
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
               System.out.println("\n==================================================");
               System.out.println("               JOB PORTAL SYSTEM");
               System.out.println("==================================================");
               System.out.println("Date & Time : " + now.format(dtf));
               System.out.println("==================================================");
               System.out.println("1. Job Requirement");
               System.out.println("2. Job Apply");
               System.out.println("3. View Applicant Details");
               System.out.println("4. Search Applicants by Job");
               System.out.println("5. Exit");
               System.out.print("Enter choice: ");
               int ch = sc.nextInt();
               sc.nextLine();
               switch (ch) {
                   case 1:
                       Statement st = con.createStatement();
                       ResultSet totalRs = st.executeQuery("SELECT COUNT(*) AS total_jobs FROM jobs");
                       if (totalRs.next()) {
                           System.out.println("\nTotal Available Jobs : " + totalRs.getInt("total_jobs"));
                       }
                       ResultSet rs = st.executeQuery(
                           "SELECT j.id, j.job_name, j.requirement, j.qualification, " +
                           "COUNT(a.job_applied) AS total_applicants " +
                           "FROM jobs j " +
                           "LEFT JOIN applicants a ON j.id = a.job_applied " +
                           "GROUP BY j.id, j.job_name, j.requirement, j.qualification " +
                           "ORDER BY j.id"
                       );
                       System.out.println("\n==========================================================================");
                       System.out.printf("%-8s %-18s %-20s %-18s %-10s%n",
                               "Job ID", "Job Name", "Requirement", "Qualification", "Applicants");
                       System.out.println("==========================================================================");
                       while (rs.next()) {
                           System.out.printf("%-8d %-18s %-20s %-18s %-10d%n",
                                   rs.getInt("id"),
                                   rs.getString("job_name"),
                                   rs.getString("requirement"),
                                   rs.getString("qualification"),
                                   rs.getInt("total_applicants"));
                       }
                       System.out.println("==========================================================================");
                       break;
                   case 2:
                       Statement st1 = con.createStatement();
                       ResultSet rs1 = st1.executeQuery("SELECT * FROM jobs");
                       System.out.println("\n=====================================");
                       System.out.printf("%-10s %-20s%n", "Job ID", "Job Name");
                       System.out.println("=====================================");
                       while (rs1.next()) {
                           System.out.printf("%-10d %-20s%n",
                                   rs1.getInt("id"),
                                   rs1.getString("job_name"));
                       }
                       System.out.println("=====================================");
                       System.out.print("\nEnter Job ID to Apply: ");
                       int job = sc.nextInt();
                       sc.nextLine();
                       System.out.print("Applicant Name   : ");
                       String name = sc.nextLine();
                       System.out.print("Age              : ");
                       int age = sc.nextInt();
                       sc.nextLine();
                       System.out.print("Phone            : ");
                       long phone = sc.nextLong();
                       sc.nextLine();
                       System.out.print("Address          : ");
                       String address = sc.nextLine();
                       System.out.print("Mail ID          : ");
                       String mail = sc.nextLine();
                       System.out.println("\nExperience Level");
                       System.out.println("1. Fresher");
                       System.out.println("2. Less Experience");
                       System.out.println("3. More than 2 Years");
                       System.out.print("Choose option    : ");
                       int expChoice = sc.nextInt();
                       sc.nextLine();
                       String experience = "";
                       switch (expChoice) {
                           case 1: experience = "Fresher"; break;
                           case 2: experience = "Less Experience"; break;
                           case 3: experience = "More than 2 Years"; break;
                           default: experience = "Not Mentioned";
                       }
                       PreparedStatement ps = con.prepareStatement(
                               "INSERT INTO applicants VALUES (app_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)"
                       );
                       ps.setString(1, name);
                       ps.setInt(2, age);
                       ps.setLong(3, phone);
                       ps.setString(4, address);
                       ps.setString(5, mail);
                       ps.setString(6, experience);
                       ps.setInt(7, job);
                       ps.executeUpdate();
                       System.out.println("\n=====================================");
                       System.out.println(" Applied Successfully!");
                       System.out.println("=====================================");
                       break;
                   case 3:
                       Statement st2 = con.createStatement();
                       ResultSet rs2 = st2.executeQuery(
                           "SELECT a.id, a.applicant_name, a.age, a.phone, a.mail_id, a.experience, j.job_name " +
                           "FROM applicants a JOIN jobs j ON a.job_applied = j.id ORDER BY a.id"
                       );
                       System.out.println("\n=================================================================================================");
                       System.out.printf("%-6s %-18s %-6s %-12s %-25s %-20s %-15s%n",
                               "ID", "Name", "Age", "Phone", "Mail ID", "Experience", "Job");
                       System.out.println("=================================================================================================");
                       while (rs2.next()) {
                           System.out.printf("%-6d %-18s %-6d %-12d %-25s %-20s %-15s%n",
                                   rs2.getInt("id"),
                                   rs2.getString("applicant_name"),
                                   rs2.getInt("age"),
                                   rs2.getLong("phone"),
                                   rs2.getString("mail_id"),
                                   rs2.getString("experience"),
                                   rs2.getString("job_name"));
                       }
                       System.out.println("=================================================================================================");
                       break;
                   case 4:
                       Statement st3 = con.createStatement();
                       ResultSet rs3 = st3.executeQuery("SELECT id, job_name FROM jobs");
                       System.out.println("\n--- JOB LIST ---");
                       while (rs3.next()) {
                           System.out.println(rs3.getInt("id") + " - " + rs3.getString("job_name"));
                       }
                       System.out.print("\nEnter Job ID to Search Applicants: ");
                       int searchJob = sc.nextInt();
                       sc.nextLine();
                       PreparedStatement sps = con.prepareStatement(
                           "SELECT a.id, a.applicant_name, a.age, a.phone, a.mail_id, a.experience " +
                           "FROM applicants a WHERE a.job_applied = ? ORDER BY a.id"
                       );
                       sps.setInt(1, searchJob);
                       ResultSet srs = sps.executeQuery();
                       System.out.println("\n======================================================================================");
                       System.out.printf("%-6s %-18s %-6s %-12s %-25s %-20s%n",
                               "ID", "Name", "Age", "Phone", "Mail ID", "Experience");
                       System.out.println("======================================================================================");
                       while (srs.next()) {
                           System.out.printf("%-6d %-18s %-6d %-12d %-25s %-20s%n",
                                   srs.getInt("id"),
                                   srs.getString("applicant_name"),
                                   srs.getInt("age"),
                                   srs.getLong("phone"),
                                   srs.getString("mail_id"),
                                   srs.getString("experience"));
                       }
                       System.out.println("======================================================================================");
                       break;
                   case 5:
                       System.out.println("\n==============================================");
                       System.out.println(" Thank you for using Job Portal System");
                       System.out.println(" Visit Again!");
                       System.out.println("==============================================");
                       System.exit(0);
                   default:
                       System.out.println("Invalid choice!");
               }
           }
       } catch (Exception e) {
           System.out.println(e);
       }
   }
}
