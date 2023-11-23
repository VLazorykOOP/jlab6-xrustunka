import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Task3GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Matrix Operation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel label = new JLabel("Enter matrix size (n <= 15):");
        JTextField textField = new JTextField();
        JButton button = new JButton("Generate Matrix");

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(textField.getText());
                    if (n <= 15) {
                        generateAndDisplayMatrix(n);
                    } else {
                        throw new CustomException("Matrix size should be less than or equal to 15.");
                        // JOptionPane.showMessageDialog(frame, "Matrix size should be less than or equal to 15.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
                } catch (CustomException ex) {
                    JOptionPane.showMessageDialog(frame, "Custom exception: " + ex.getMessage());
                }
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }

    private static void generateAndDisplayMatrix(int n) throws CustomException {
        int[][] A = generateRandomMatrix(n);
        int[][] B = generateRandomMatrix(n);
        int[][] X = new int[n][n];

        // Assuming the rest of the code for matrix operation is in performMatrixOperation method
        performMatrixOperation(A, B, X);

        // Display the generated matrices
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        System.out.println("Matrix X:");
        printMatrix(X);
    }

    private static void performMatrixOperation(int[][] A, int[][] B, int[][] X) throws CustomException {
        // Your matrix operations here
    }

    private static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(10); // Adjust the range of random values as needed
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Define your CustomException class
    static class CustomException extends ArithmeticException {
        public CustomException(String message) {
            super(message);
        }
    }
}
