import javax.swing.*;
import java.awt.*;
public class SFrame extends JFrame{
    ImageIcon img,img1;
    JPanel panel,panel2,panel3,panel4,panel5;
    JButton calcgbtn,addcbtn,exitbtn,clearbtn;
    JLabel ptgp,cocr,cn,ccr,cnu,stcr,imgl,imgl1;
    JTextField ptgptf,cocrtf,cntf,ccrtf,cnutf,stcrtf;
    JTextArea ta;
    JScrollPane scrollPane;
    public SFrame(){
        this.setTitle("AIUB CGPA Calculator");
        this.setSize(600,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(23, 190, 163));
        this.add(panel);
        
        panel2=new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(20,140,545,200);
        panel2.setBackground(new Color(30, 131, 177));
        panel.add(panel2);

        panel3=new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(20,350,545,70);
        panel3.setBackground(new Color(30, 131, 177));
        panel.add(panel3);

        panel4=new JPanel();
        panel4.setLayout(null);
        panel4.setBounds(20,430,545,89);
        panel4.setBackground(new Color(93, 131, 157));
        panel.add(panel4);

        panel5=new JPanel();
        panel5.setLayout(null);
        panel5.setBounds(20,530,545,170);
        panel5.setBackground(new Color(93, 131, 157));
        panel.add(panel5);

        calcgbtn=new JButton("Calculate CGPA");
        calcgbtn.setBounds(20,20,150,30);
        calcgbtn.addActionListener(e -> calculateCGPA());
        panel3.add(calcgbtn);

        addcbtn=new JButton("Add Course");
        addcbtn.setBounds(350,10,150,30);
        addcbtn.addActionListener(e -> addCourse());
        panel5.add(addcbtn);

        exitbtn=new JButton("Exit");
        exitbtn.setBounds(380,20,150,30);
        exitbtn.addActionListener(e -> System.exit(0));
        panel3.add(exitbtn);

        clearbtn=new JButton("Clear");
        clearbtn.setBounds(200,20,150,30);
        clearbtn.addActionListener(e -> clearFields());
        panel3.add(clearbtn);

        ptgp=new JLabel("Previous Total Grade Point: ");
        ptgp.setBounds(20,10,200,30);
        panel4.add(ptgp);
    
        ptgptf=new JTextField();
        ptgptf.setBounds(220,10,100,30);
        panel4.add(ptgptf);

        cocr=new JLabel("Completed Credits: ");
        cocr.setBounds(20,50,200,30);
        panel4.add(cocr);

        cocrtf=new JTextField();
        cocrtf.setBounds(220,50,100,30);
        panel4.add(cocrtf);

        cn=new JLabel("Course Name: ");
        cn.setBounds(20,10,200,30);
        panel5.add(cn);

        cntf=new JTextField();
        cntf.setBounds(120,10,200,30);
        panel5.add(cntf);

        ccr=new JLabel("Course Credit: ");
        ccr.setBounds(20,50,200,30);
        panel5.add(ccr);

        ccrtf=new JTextField();
        ccrtf.setBounds(120,50,200,30);
        panel5.add(ccrtf);

        cnu=new JLabel("Gained Number in Course: ");
        cnu.setBounds(20,90,200,30);
        panel5.add(cnu);

        cnutf=new JTextField();
        cnutf.setBounds(220,90,100,30);
        panel5.add(cnutf);

        stcr=new JLabel("Semester Total Credit: ");
        stcr.setBounds(20,130,200,30);
        panel5.add(stcr);

        stcrtf=new JTextField();
        stcrtf.setBounds(220,130,100,30);
        panel5.add(stcrtf);

        ta=new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setFont(new Font("Arial", Font.PLAIN, 14));
        scrollPane = new JScrollPane(ta);
        scrollPane.setBounds(20, 20, 500, 150);
        panel2.add(scrollPane);

        ImageIcon icon = new ImageIcon("Images//AIUB.png");
        Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
        imgl = new JLabel(new ImageIcon(img));
        imgl.setBounds(20, 10, 145, 120);
        panel.add(imgl);

        JLabel title = new JLabel("American International University-Bangladesh");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBounds(180, 50, 400, 30);
        panel.add(title);
    }
    private double semesterTotalGP = 0.0;
    private double semesterTotalCredits = 0.0;
    private void addCourse() {
        try {
            String courseName = cntf.getText();
            double courseCredit = Double.parseDouble(ccrtf.getText());
            double gainedNumber = Double.parseDouble(cnutf.getText());

            String grade = CourseCalculator.getGrade(gainedNumber);
            double gradePoint = CourseCalculator.getGradePoint(grade);
            double totalGP = gradePoint * courseCredit;
            semesterTotalGP += totalGP;
            semesterTotalCredits += courseCredit;
            ta.append("Course: " + courseName + ", Number: " + gainedNumber + ", Grade: " + grade + ", Point: " + gradePoint + ", Credit: " + courseCredit + "\n");
            cntf.setText("");
            ccrtf.setText("");
            cnutf.setText("");

        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }
    private void calculateCGPA() {
        try {
            double prevTotalGP = Double.parseDouble(ptgptf.getText());
            double prevCredits = Double.parseDouble(cocrtf.getText());
            stcrtf.setText(String.valueOf(semesterTotalCredits));

            double finalCGPA = CourseCalculator.calculateCGPA(prevTotalGP, prevCredits, semesterTotalGP, semesterTotalCredits);

            ta.append("\nFinal CGPA: " + String.format("%.2f", finalCGPA) + "\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please check previous data inputs.");
        }
    }
    private void clearFields() {
        ptgptf.setText("");
        cocrtf.setText("");
        cntf.setText("");
        ccrtf.setText("");
        cnutf.setText("");
        stcrtf.setText("");
        ta.setText("");
        semesterTotalGP = 0.0;
        semesterTotalCredits = 0.0;
    }
}
