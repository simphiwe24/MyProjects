import java.io.*;
import java.util.Scanner;

/**
 * This program reads student information and test results from various data
 * files, and generates a report, which includes student final results.
 * 
 * @author Luke
 */
public class FinalMarkReportGenerator {

  /**
   * The entry-point to the application. First, all test results are loaded from
   * data files into int arrays. Second, all student information is loaded into
   * an array of student objects created from a data file. Third, a Marks object
   * is created for each student object, from the various test result arrays,
   * and associated with that student object. Lastly, the report is generated to
   * a data file.
   * 
   * @param args
   *          none
   * @throws IOException
   *           this is thrown if something goes wrong with reading or writing
   *           files.
   */
  public static void main(String[] args) throws IOException {
    int[] test1Marks = loadMarks("test1.dat");
    int[] test2Marks = loadMarks("test2.dat");
    int[] pracMarks = loadMarks("prac.dat");
    int[] examMarks = loadMarks("exam.dat");
    Student[] students = loadStudents("students.dat");
    for (int i = 0; i < students.length; i++) {
      Marks marks = new Marks(test1Marks[i], test2Marks[i], pracMarks[i], examMarks[i]);
      students[i].setMarks(marks);
    }
    generateReport(students, "report.dat");
    System.out.println("Report Generated Successfully!");
  }

  /**
   * This method loads test results for all students from a specified data file.
   * 
   * NB: The first line of the file contains the number of results in the file!
   * i.e. If the number is 5, then there are five results after the first line.
   * 
   * @param fileName
   *          the file containing the results.
   * @return an array of ints representing the results.
   * @throws IOException
   *           thrown if something goes wrong reading from the data file.
   */
  private static int[] loadMarks(String fileName) throws IOException {
    Scanner markScanner = new Scanner(new File(fileName));
    
    String line = markScanner.nextLine(); // "94 STUDENTS"
    String intString = line.substring(0, line.indexOf(" ")); // "94"
    int numRecords = Integer.valueOf(intString).intValue(); // 94
    
    int[] marks = new int[numRecords];
    for (int i = 0; i < marks.length; i++) {
      marks[i] = markScanner.nextInt();
    }
    
    markScanner.close();
    return marks;
  }

  /**
   * This method loads all students information from a data file into an array
   * of Student objects.
   *    
   * NB: The first line of the file contains the number of results in the file!
   * i.e. If the number is 5, then there are five results after the first line.
   * 
   * @param fileName
   *          the file containing the student information.
   * @return an array of Student objects.
   * @throws IOException
   *           thrown if something goes wrong reading from the data file.
   */
  private static Student[] loadStudents(String fileName) throws IOException {
    Scanner studentScanner = new Scanner(new File(fileName));

    String line = studentScanner.nextLine(); // "94 STUDENTS"
    String intString = line.substring(0, line.indexOf(" ")); // "94"
    int numRecords = Integer.valueOf(intString).intValue(); // 94

    Student[] students = new Student[numRecords];
    for (int i = 0; i < students.length; i++) {
      line = studentScanner.nextLine(); // "Thimershen Achary 216005583"
      int start = 0;
      int end = line.indexOf(" ");
      
      String firstName = line.substring(start, end);
      start = ++end;
      end = line.indexOf(" ", start);
      String lastName = line.substring(start, end);
      start = ++end;
      end = line.length();
      String stuNum = line.substring(start, end);
      
      students[i] = new Student(firstName, lastName, stuNum);
    }
    
    studentScanner.close();
    return students;
  }

  /**
   * This method generates the report of student results.
   * 
   * @param students
   *          an array of Student objects.
   * @param fileName
   *          the name of the report file.
   * @throws IOException
   *           thrown if something goes wrong writing the report file.
   */
  private static void generateReport(Student[] students, String fileName) throws IOException {
    FileWriter reportWriter = new FileWriter(fileName);
    for (Student student : students) {
      reportWriter.write(student.toString() + "\n");
    }
    reportWriter.close();
  }

}
